package com.dahhong.whoami.auth.adapter.in;

import com.dahhong.whoami.auth.application.port.in.LoginKakaoUseCase;
import com.dahhong.whoami.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/kakao")
public class KakaoOauthController {

    private final LoginKakaoUseCase loginKakaoUseCase;

    @GetMapping("/login")
    public ResponseEntity<?> loginKakao() {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(loginKakaoUseCase.getKakaoOauthURI());
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @GetMapping("/callback")
    public ResponseEntity<ApiResponse<Void>> callbackByKakao(@Param("code") String code) {
        System.out.println("code = " + code);
        loginKakaoUseCase.loginKakao(code);
        return ResponseEntity.ok(ApiResponse.success());
    }

}
