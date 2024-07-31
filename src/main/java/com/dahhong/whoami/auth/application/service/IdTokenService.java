package com.dahhong.whoami.auth.application.service;

import com.dahhong.whoami.auth.application.service.dto.IdTokenPayloadResponseDto;
import lombok.RequiredArgsConstructor;
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
public class IdTokenService {

    private final RestTemplate restTemplate;

    private final String ROOT_URI = "https://kauth.kakao.com";

    private final String TOKEN_INFO_URL = "/oauth/tokeninfo";

    private IdTokenPayloadResponseDto getIdTokenPayload(String idToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> idTokenRequestBody = new LinkedMultiValueMap<>();
        idTokenRequestBody.add("id_token", idToken);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(idTokenRequestBody, headers);

        URI uri = UriComponentsBuilder
                .fromUriString(ROOT_URI)
                .path(TOKEN_INFO_URL)
                .build()
                .toUri();

        try {
            ResponseEntity<IdTokenPayloadResponseDto> response = restTemplate.postForEntity(uri, request, IdTokenPayloadResponseDto.class);
            return response.getBody();
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
            throw new RestClientException("카카오 서버에서 유저의 고유 ID를 받아오는 것에 실패했습니다.");
        }
    }

    public String getUserId(String idToken) {
        IdTokenPayloadResponseDto idTokenPayload = getIdTokenPayload(idToken);
        return idTokenPayload.getSub();
    }

}
