package com.dahhong.whoami.auth.application.service;

import com.dahhong.whoami.auth.application.service.dto.UserInfoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class GetKaKaoUserInfoService {

    private final RestTemplate restTemplate;

    private final String ROOT_URI = "https://kapi.kakao.com";

    private final String USER_INFO_URL = "/v2/user/me";

    public UserInfoResponseDto getUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);

        URI uri = UriComponentsBuilder
                .fromUriString(ROOT_URI)
                .path(USER_INFO_URL)
                .queryParam("secure_resource", true)
                .queryParam("property_keys", "[\"kakao_account.profile\"]")
                .build()
                .toUri();

        try {
            ResponseEntity<UserInfoResponseDto> response = restTemplate.exchange(uri, HttpMethod.GET, request, UserInfoResponseDto.class);
            return response.getBody();
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
            throw new RestClientException("카카오 서버에서 유저 정보를 받아오는 것에 실패했습니다.");
        }
    }

}
