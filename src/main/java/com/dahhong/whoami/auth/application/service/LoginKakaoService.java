package com.dahhong.whoami.auth.application.service;

import com.dahhong.whoami.auth.application.port.in.LoginKakaoUseCase;
import com.dahhong.whoami.auth.application.service.dto.GetTokenResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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
public class LoginKakaoService implements LoginKakaoUseCase {

    private final String ROOT_URI = "https://kauth.kakao.com";

    private final String AUTHORIZE_URL = "/oauth/authorize";

    private final String TOKEN_URL = "/oauth/token";

    @Value("${kakao.client.id}")
    private String clientID;

    @Value("${kakao.redirect.uri}")
    private String redirectURI;

    private RestTemplate kakaoRestTemplate() {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        return builder
                .rootUri(ROOT_URI)
                .build();
    }

    private String publishTokenByCode(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> tokenRequestBody = new LinkedMultiValueMap<>();
        tokenRequestBody.add("grant_type", "authorization_code");
        tokenRequestBody.add("client_id", clientID);
        tokenRequestBody.add("redirect_uri", redirectURI);
        tokenRequestBody.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(tokenRequestBody, headers);

        URI uri = UriComponentsBuilder
                .fromUriString(ROOT_URI)
                .path(TOKEN_URL)
                .build()
                .toUri();

        try {
            RestTemplate restTemplate = kakaoRestTemplate();
            ResponseEntity<GetTokenResponseDto> response = restTemplate.postForEntity(uri, request, GetTokenResponseDto.class);
            System.out.println("response: " + response);
            return response.getBody().toString();
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
            throw new RestClientException("카카오 서버에서 토큰을 받아오는 것에 실패했습니다.");
        }
    }

    @Override
    public URI getKakaoOauthURI() {
        return UriComponentsBuilder
                .fromUriString(ROOT_URI)
                .path(AUTHORIZE_URL)
                .queryParam("client_id", clientID)
                .queryParam("redirect_uri", redirectURI)
                .queryParam("response_type", "code")
                .build()
                .toUri();
    }

    @Override
    public void loginKakao(String code) {
        System.out.println(publishTokenByCode(code));
    }

}
