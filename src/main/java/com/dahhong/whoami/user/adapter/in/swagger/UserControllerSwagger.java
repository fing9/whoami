package com.dahhong.whoami.user.adapter.in.swagger;

import com.dahhong.whoami.global.response.ApiResponse;
import com.dahhong.whoami.user.adapter.in.dto.UserInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "유저", description = "유저 관련 API")
public interface UserControllerSwagger {

	@Operation(summary = "유저 정보 조회", description = "현재 인증된 유저의 정보를 조회합니다. 별도의 인자 없이 요청만 하면 됨. 백엔드에서 현재 로그인 정보를 확인함")
	ResponseEntity<?> getUser(@AuthenticationPrincipal String userId);

	@Operation(summary = "모든 유저 정보 조회(ADMIN)", description = "모든 유저의 정보를 조회합니다. 이 API는 관리자만 접근할 수 있습니다.")
	ResponseEntity<?> getAllUsers();

	@Operation(summary = "특정 유저 정보 조회(본인 정보가 아닌 경우 ADMIN이어야 함)", description = "특정 유저의 정보를 조회합니다. 관리자는 다른 유저의 정보를 조회할 수 있습니다.")
	@Parameters({
			@Parameter(name = "id", description = "조회할 유저의 ID", required = true)
	})
	ResponseEntity<?> getUser(@PathVariable String id, @AuthenticationPrincipal String userId);
}
