package com.dahhong.whoami.auth.adapter.in;

import com.dahhong.whoami.auth.adapter.in.dto.KakaoCallbackResponseDto;
import com.dahhong.whoami.auth.application.port.in.LoginKakaoUseCase;
import com.dahhong.whoami.auth.application.port.in.LogoutKakaoUseCase;
import com.dahhong.whoami.auth.application.port.in.QuitKakaoUseCase;
import com.dahhong.whoami.global.response.ApiResponse;
import com.dahhong.whoami.user.application.port.in.QuitUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/kakao")
public class KakaoOauthController {

    private final LoginKakaoUseCase loginKakaoUseCase;

    private final LogoutKakaoUseCase logoutKakaoUseCase;

    private final QuitKakaoUseCase quitKakaoUseCase;

    private final QuitUserUseCase quitUserUseCase;

    @GetMapping("/login")
    public ResponseEntity<?> loginKakao() {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(loginKakaoUseCase.getKakaoOauthURI());
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @GetMapping("/callback")
    public ResponseEntity<ApiResponse<KakaoCallbackResponseDto>> callbackByKakao(@Param("code") String code) {
        String accessToken = loginKakaoUseCase.loginKakao(code);
        KakaoCallbackResponseDto responseDto = KakaoCallbackResponseDto.builder()
                .accessToken(accessToken)
                .build();
        return ResponseEntity.ok(ApiResponse.success(responseDto));
    }

    @DeleteMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout(@Param("accessToken") String accessToken) {
        logoutKakaoUseCase.logoutKakao(accessToken);
        return ResponseEntity.ok(ApiResponse.success());
    }

    @DeleteMapping("/quit")
    public ResponseEntity<ApiResponse<Void>> quit(@Param("accessToken") String accessToken) {
        String userId = quitKakaoUseCase.quitKakao(accessToken).getId();
        quitUserUseCase.quitUser(userId);
        return ResponseEntity.ok(ApiResponse.success());
    }

}
