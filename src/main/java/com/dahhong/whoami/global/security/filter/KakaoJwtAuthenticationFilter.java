package com.dahhong.whoami.global.security.filter;

import com.dahhong.whoami.global.security.authenticationProvider.KakaoAuthenticationProvider;
import com.dahhong.whoami.global.security.filter.dto.AccessTokenVerificationResponseDto;
import com.dahhong.whoami.user.application.port.out.UserQueryPort;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

@RequiredArgsConstructor
public class KakaoJwtAuthenticationFilter extends GenericFilterBean {

    private final UserQueryPort userQueryPort;

    private final KakaoAuthenticationProvider kakaoAuthenticationProvider;

    private final RestTemplate restTemplate;

    private final String ROOT_URI = "https://kapi.kakao.com";

    private final String ACCESS_TOKEN_VERIFICATION_URL = "/v1/user/access_token_info";

    private boolean verification(String id) {
        return userQueryPort.findById(id).isPresent();
    }

    private String extractUserId(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);

        URI uri = UriComponentsBuilder
                .fromUriString(ROOT_URI)
                .path(ACCESS_TOKEN_VERIFICATION_URL)
                .build()
                .toUri();

        try {
            ResponseEntity<AccessTokenVerificationResponseDto> response = restTemplate.exchange(uri, HttpMethod.GET, request, AccessTokenVerificationResponseDto.class);
            return response.getBody().getId().toString();
        } catch (RestClientException | NullPointerException e) {
            System.out.println(e.getMessage());
            throw new RestClientException("카카오 서버에서 유저 토큰을 인증하는 것에 실패했습니다.");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Authentication authenticate;

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String accessToken = request.getHeader("Authorization") != null ? request.getHeader("Authorization").substring(7) : null;

        // 엑세스 토큰이 NULL일 경우 다음 필터에서 인증의 책임을 가진다
        if (accessToken == null) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String userId = extractUserId(accessToken);
        if (verification(userId)) {
            try {
                authenticate = kakaoAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(userId, ""));
                SecurityContextHolder.getContext().setAuthentication(authenticate);
            } catch(Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                JSONObject resJson = new JSONObject();
                resJson.put("code", 401);
                resJson.put("message", e.getMessage());

                response.getWriter().write(resJson.toString());
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

}
