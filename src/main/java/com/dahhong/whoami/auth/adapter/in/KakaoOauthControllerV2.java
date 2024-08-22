package com.dahhong.whoami.auth.adapter.in;

import com.dahhong.whoami.auth.adapter.in.dto.KakaoCallbackResponseDto;
import com.dahhong.whoami.auth.adapter.in.swagger.KakaoOauthControllerSwaggerV2;
import com.dahhong.whoami.auth.application.port.in.KakaoTokenRefreshUseCase;
import com.dahhong.whoami.auth.application.port.in.LoginKakaoUseCase;
import com.dahhong.whoami.auth.application.port.in.LogoutKakaoUseCase;
import com.dahhong.whoami.auth.application.port.in.QuitKakaoUseCase;
import com.dahhong.whoami.auth.application.service.dto.TokenRefreshResponseDto;
import com.dahhong.whoami.global.response.ApiResponse;
import com.dahhong.whoami.user.application.port.in.QuitUserUseCase;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/auth/kakao")
public class KakaoOauthControllerV2 implements KakaoOauthControllerSwaggerV2 {

	private final LoginKakaoUseCase loginKakaoUseCase;

	private final KakaoTokenRefreshUseCase kakaoTokenRefreshUseCase;

	@GetMapping("/callback")
	public ResponseEntity<ApiResponse<KakaoCallbackResponseDto>> callbackByKakao(@Param("code") String code, HttpServletResponse response) {
		KakaoCallbackResponseDto responseDto = loginKakaoUseCase.loginKakao(code);

		// Refresh Token은 cookie에 직접 넣어주기.
		Cookie cookie = new Cookie("Refresh_Token", responseDto.getRefreshToken());
		responseDto.setRefreshToken(""); //쿠키에 넣어줄거임
		cookie.setMaxAge(7 * 24 * 60 * 60); //만료 일주일
		cookie.setPath("/");
		cookie.setSecure(true);
		cookie.setHttpOnly(true);
		response.addCookie(cookie);

		return ResponseEntity.ok(ApiResponse.success(responseDto));
	}

	@PostMapping("/refresh")
	public ResponseEntity<ApiResponse<TokenRefreshResponseDto>> refresh(@CookieValue("Refresh_Token") String refreshToken, HttpServletResponse response) {
		TokenRefreshResponseDto newTokenPair = kakaoTokenRefreshUseCase.refresh(refreshToken);

		if(newTokenPair.getRefresh_token() != null) { //refresh token도 재발급된 경우
			// Refresh Token은 cookie에 직접 넣어주기.
			Cookie cookie = new Cookie("Refresh_Token", newTokenPair.getRefresh_token());
			newTokenPair.setRefresh_token(""); //쿠키에 넣어줄거임
			cookie.setMaxAge(7 * 24 * 60 * 60); //만료 일주일
			cookie.setPath("/");
			cookie.setSecure(true);
			cookie.setHttpOnly(true);
			response.addCookie(cookie);

		}

		return ResponseEntity.ok(ApiResponse.success(newTokenPair));
	}
}
