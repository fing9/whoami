package com.dahhong.whoami.page.adapter.in.swagger;

import com.dahhong.whoami.page.adapter.in.dto.PageRequestDto;
import com.dahhong.whoami.page.domain.entity.Page;
import com.dahhong.whoami.reply.adapter.in.dto.ReplyRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "페이지", description = "페이지 관련 CRUD API")
public interface PageControllerSwagger {

	@Operation(summary = "페이지 조회", description = "특정 ID에 해당하는 페이지를 조회합니다.")
	@Parameters({
			@Parameter(name = "id", description = "조회할 페이지의 ID", required = true)
	})
	ResponseEntity<?> getPage(Long id);

	@Operation(summary = "모든 페이지 조회", description = "모든 페이지를 조회합니다.")
	ResponseEntity<?> getAllPages();

	@Operation(summary = "페이지 생성", description = "새로운 페이지를 생성합니다.")
	@Parameters()
	ResponseEntity<?> createPage(PageRequestDto pageRequest, @AuthenticationPrincipal String userId);

	@Operation(summary = "페이지 수정", description = "특정 ID에 해당하는 페이지를 수정합니다.")
	@Parameters({
			@Parameter(name = "id", description = "수정할 페이지의 ID", required = true),
	})
	ResponseEntity<?> updatePage(Long id, PageRequestDto pageRequest, @AuthenticationPrincipal String userId);

	@Operation(summary = "페이지 삭제", description = "특정 ID에 해당하는 페이지를 삭제합니다.")
	@Parameters({
			@Parameter(name = "id", description = "삭제할 페이지의 ID", required = true)
	})
	ResponseEntity<?> deletePage(Long id , @AuthenticationPrincipal String userId);

	@Operation(summary = "특정 사용자의 모든 페이지 조회", description = "특정 사용자의 모든 페이지를 조회합니다.")
	@Parameters({
			@Parameter(name = "userId", description = "조회할 사용자의 ID", required = true)
	})
	ResponseEntity<?> getPageOfUser(String userId);

	@Operation(summary = "특정 페이지에 답변 생성", description = "특정 페이지에 답변을 생성합니다.")
	@Parameters({
			@Parameter(name = "pageId", description = "답변을 추가할 페이지의 ID", required = true)
	})
	ResponseEntity<?> createReplyToPage(Long pageId, ReplyRequestDto replyRequest);

	@Operation(summary = "특정 페이지의 답변 조회", description = "특정 페이지에 대한 모든 답변을 조회합니다.")
	@Parameters({
			@Parameter(name = "pageId", description = "답변을 조회할 페이지의 ID", required = true)
	})
	ResponseEntity<?> getRepliesOfPage(Long pageId);
}