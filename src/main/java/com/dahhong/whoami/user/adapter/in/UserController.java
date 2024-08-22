package com.dahhong.whoami.user.adapter.in;

import com.dahhong.whoami.global.exception.customException.AuthorizationFailureException;
import com.dahhong.whoami.global.response.ApiResponse;
import com.dahhong.whoami.user.adapter.in.dto.UserInfoDto;
import com.dahhong.whoami.user.adapter.in.swagger.UserControllerSwagger;
import com.dahhong.whoami.user.application.port.in.GetUserUseCase;
import com.dahhong.whoami.user.domain.entity.Role;
import com.dahhong.whoami.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController implements UserControllerSwagger {
	private final GetUserUseCase getUserUseCase;

	@GetMapping("/info")
	public ResponseEntity<?> getUser(@AuthenticationPrincipal String userId) {
		User user = getUserUseCase.getUser(userId);
		return ResponseEntity.ok(ApiResponse.success(UserInfoDto.of(user)));
	}

	@GetMapping("/info/all")
	public ResponseEntity<?> getAllUsers(@AuthenticationPrincipal String userId) {
		if (getUserUseCase.getUser(userId).getRole() != Role.ADMIN) {
			throw new AuthorizationFailureException("다른 유저 정보에 접근할 수 있는 권한이 없습니다.", null);
		}
		List<UserInfoDto> users = getUserUseCase.getAllUsers().stream().map((user) -> UserInfoDto.of(user)).toList();
		return ResponseEntity.ok(ApiResponse.success(users));
	}

	@GetMapping("/info/{id}")
	public ResponseEntity<?> getUser(@PathVariable String id, @AuthenticationPrincipal String userId) {
		if (!id.equals(userId) && getUserUseCase.getUser(userId).getRole() != Role.ADMIN) {
			throw new AuthorizationFailureException("다른 유저 정보에 접근할 수 있는 권한이 없습니다.", null);
		}
		User user = getUserUseCase.getUser(id);
		return ResponseEntity.ok(ApiResponse.success(UserInfoDto.of(user)));
	}
}
