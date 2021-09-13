package com.cybersecurity.productservice.feign;

import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ramesh.Yaleru on 9/10/2021
 */
@Configuration
public class AdidasServiceFeignConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("content-type","application/json");
            requestTemplate.header("connection","keep-alive");
            requestTemplate.header("user-agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.63 Mobile Safari/537.36");
        };
    }
    @Bean
    Logger.Level feignLoggerLevelAdidas() {
        return Logger.Level.FULL;
    }
}
