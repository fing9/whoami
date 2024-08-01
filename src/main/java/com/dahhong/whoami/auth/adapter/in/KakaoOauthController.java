package com.dahhong.whoami.auth.adapter.in;

import com.dahhong.whoami.auth.adapter.in.dto.KakaoCallbackResponseDto;
import com.dahhong.whoami.auth.application.port.in.KakaoTokenRefreshUseCase;
import com.dahhong.whoami.auth.application.port.in.LoginKakaoUseCase;
import com.dahhong.whoami.auth.application.port.in.LogoutKakaoUseCase;
import com.dahhong.whoami.auth.application.port.in.QuitKakaoUseCase;
import com.dahhong.whoami.auth.application.service.dto.TokenRefreshResponseDto;
import com.dahhong.whoami.global.response.ApiResponse;
import com.dahhong.whoami.user.application.port.in.QuitUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/kakao")
public class KakaoOauthController {

    private final LoginKakaoUseCase loginKakaoUseCase;

    private final LogoutKakaoUseCase logoutKakaoUseCase;

    private final QuitKakaoUseCase quitKakaoUseCase;

    private final QuitUserUseCase quitUserUseCase;

    private final KakaoTokenRefreshUseCase kakaoTokenRefreshUseCase;

    @GetMapping("/login")
    public ResponseEntity<?> loginKakao() {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(loginKakaoUseCase.getKakaoOauthURI());
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @GetMapping("/callback")
    public ResponseEntity<ApiResponse<KakaoCallbackResponseDto>> callbackByKakao(@Param("code") String code) {
        KakaoCallbackResponseDto responseDto = loginKakaoUseCase.loginKakao(code);
        return ResponseEntity.ok(ApiResponse.success(responseDto));
    }

    @DeleteMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout(@RequestHeader("Authorization") String accessToken) {
        accessToken = accessToken.replace("Bearer ", "");
        logoutKakaoUseCase.logoutKakao(accessToken);
        return ResponseEntity.ok(ApiResponse.success());
    }

    @DeleteMapping("/quit")
    public ResponseEntity<ApiResponse<Void>> quit(@RequestHeader("Authorization") String accessToken) {
        accessToken = accessToken.replace("Bearer ", "");
        String userId = quitKakaoUseCase.quitKakao(accessToken).getId();
        quitUserUseCase.quitUser(userId);
        return ResponseEntity.ok(ApiResponse.success());
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<TokenRefreshResponseDto>> refresh(@RequestHeader("Refresh_Token") String refreshToken) {
        TokenRefreshResponseDto newTokenPair = kakaoTokenRefreshUseCase.refresh(refreshToken);
        return ResponseEntity.ok(ApiResponse.success(newTokenPair));
    }

}
