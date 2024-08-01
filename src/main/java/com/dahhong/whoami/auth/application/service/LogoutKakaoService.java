package com.dahhong.whoami.auth.application.service;

import com.dahhong.whoami.auth.application.port.in.LogoutKakaoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class LogoutKakaoService implements LogoutKakaoUseCase {

    private final RestTemplate restTemplate;

    private final String ROOT_URI = "https://kapi.kakao.com";

    private final String LOGOUT_URL = "/v1/user/logout";

    @Override
    public void logoutKakao(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);

        URI uri = UriComponentsBuilder
                .fromUriString(ROOT_URI)
                .path(LOGOUT_URL)
                .build()
                .toUri();

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(uri, request, String.class);
            if (response.getStatusCode() != HttpStatus.OK) {
                throw new RestClientException("카카오 서버의 로그아웃 요청에 실패했습니다.");
            }
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
            throw new RestClientException("카카오 서버의 로그아웃 요청에 실패했습니다.");
        }
    }

}
