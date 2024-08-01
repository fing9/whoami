package com.dahhong.whoami.auth.adapter.in.swagger;

import com.dahhong.whoami.auth.adapter.in.dto.KakaoCallbackResponseDto;
import com.dahhong.whoami.auth.application.service.dto.TokenRefreshResponseDto;
import com.dahhong.whoami.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

public interface KakaoOauthControllerSwagger {

    @Tag(name = "카카오 로그인/회원가입", description = "카카오 로그인/회원가입")
    @Operation(summary = "카카오 로그인/회원가입", description = "카카오 OAuth2를 통해 로그인/회원가입(통합)을 시도합니다.\n카카오 인증 페이지로 리다이렉션 됩니다.")
    ResponseEntity<?> loginKakao();

    @Tag(name = "카카오 로그인/회원가입", description = "카카오 로그인 콜백")
    @Operation(summary = "카카오 로그인 콜백", description = "카카오 OAuth2 정보 동의를 마무리 한 뒤, 리다이렉트 되는 콜백 URL 입니다.")
    @Parameters({
            @Parameter(name = "code", description = "카카오 정보 동의시에 자동으로 발급되는 Code 값 (일반적으로 직접 넣어줄 필요가 없습니다.)", required = true)
    })
    ResponseEntity<ApiResponse<KakaoCallbackResponseDto>> callbackByKakao(String code);

    @Tag(name = "카카오 로그아웃/회원탈퇴", description = "카카오 로그아웃")
    @Operation(summary = "카카오 로그아웃", description = "카카오 OAuth2를 통해 로그인한 사용자를 로그아웃 합니다.")
    ResponseEntity<ApiResponse<String>> logout(String accessToken);

    @Tag(name = "카카오 로그아웃/회원탈퇴", description = "카카오 회원탈퇴")
    @Operation(summary = "카카오 회원탈퇴", description = "카카오 OAuth2를 통해 회원가입한 사용자를 회원탈퇴 합니다.")
    ResponseEntity<ApiResponse<Void>> quit(String accessToken);

    @Tag(name = "카카오 JWT 토큰 갱신", description = "카카오 토큰 갱신")
    @Operation(summary = "카카오 토큰 갱신", description = "카카오 OAuth2를 통해 로그인한 사용자의 토큰을 Refresh Token을 사용해 갱신합니다.")
    ResponseEntity<ApiResponse<TokenRefreshResponseDto>> refresh(String refreshToken);

}
