package com.dahhong.whoami.auth.application.service;

import com.dahhong.whoami.auth.application.port.in.KakaoTokenRefreshUseCase;
import com.dahhong.whoami.auth.application.service.dto.TokenRefreshResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class KakaoTokenRefreshService implements KakaoTokenRefreshUseCase {

    private final RestTemplate restTemplate;

    private final String ROOT_URI = "https://kauth.kakao.com";

    private final String ACCESS_TOKEN_REFRESH_URL = "/oauth/token";

    @Value("${kakao.client.id}")
    private String clientID;

    public TokenRefreshResponseDto refresh(String refreshToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> tokenRefreshRequestBody = new LinkedMultiValueMap<>();
        tokenRefreshRequestBody.add("grant_type", "refresh_token");
        tokenRefreshRequestBody.add("client_id", clientID);
        tokenRefreshRequestBody.add("refresh_token", refreshToken);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(tokenRefreshRequestBody, headers);

        URI uri = UriComponentsBuilder
                .fromUriString(ROOT_URI)
                .path(ACCESS_TOKEN_REFRESH_URL)
                .build()
                .toUri();

        try {
            ResponseEntity<TokenRefreshResponseDto> response = restTemplate.exchange(uri, HttpMethod.POST, request, TokenRefreshResponseDto.class);
            return response.getBody();
        } catch (RestClientException | NullPointerException e) {
            throw new RestClientException("토큰 재발급에 실패했습니다.");
        }
    }

}
