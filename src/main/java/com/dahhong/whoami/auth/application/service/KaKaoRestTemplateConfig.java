package com.dahhong.whoami.auth.application.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class KaKaoRestTemplateConfig {

    private final String ROOT_URI = "https://kauth.kakao.com";

    @Bean
    public RestTemplate kakaoRestTemplate(RestTemplateBuilder builder) {
        return builder
                .rootUri(ROOT_URI)
                .build();
    }

}
