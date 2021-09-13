package com.cybersecurity.reviewservice.service;

import com.cybersecurity.reviewservice.config.CustomUserDetails;
import com.cybersecurity.reviewservice.entity.User;
import com.cybersecurity.reviewservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Ramesh.Yaleru on 9/10/2021
 */
@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    /**
     * Load User by userName
     * @param userName
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException {
        final Optional<User> optionalUser = userRepository.findByUserName(userName);
        return optionalUser.map(CustomUserDetails::new).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User = %s does not exists", userName)));
    }
}
