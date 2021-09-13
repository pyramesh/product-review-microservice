package com.cybersecurity.reviewservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Ramesh.Yaleru on 9/10/2021
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String[] WHITELIST_PATTERNS = {"/anonymous", "/h2-console/**"};

    @Qualifier("customUserDetailsService")
    @Autowired
    UserDetailsService detailsService;

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detailsService);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.httpBasic()
                .and().authorizeRequests().antMatchers(HttpMethod.GET,"/v1/review/*").permitAll()
                .and().authorizeRequests().antMatchers(HttpMethod.GET,"/v1/review").permitAll()
                .and().authorizeRequests().antMatchers("/console/**").permitAll()
                .and().authorizeRequests().anyRequest().authenticated()
                .and().csrf().disable()
                .headers().frameOptions().disable();
                //.headers().frameOptions().sameOrigin();
        //here we will not create a session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
