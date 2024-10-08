package com.dahhong.whoami.page.adapter.in;

import com.dahhong.whoami.global.exception.customException.AuthorizationFailureException;
import com.dahhong.whoami.global.response.ApiResponse;
import com.dahhong.whoami.page.adapter.in.dto.GetPageResponseDto;
import com.dahhong.whoami.page.adapter.in.dto.PageRequestDto;
import com.dahhong.whoami.page.adapter.in.dto.CreatePageResponseDto;
import com.dahhong.whoami.page.adapter.in.swagger.PageControllerSwagger;
import com.dahhong.whoami.page.application.port.in.CreatePageUseCase;
import com.dahhong.whoami.page.application.port.in.DeletePageUseCase;
import com.dahhong.whoami.page.application.port.in.GetPageUseCase;
import com.dahhong.whoami.page.application.port.in.UpdatePageUseCase;
import com.dahhong.whoami.page.domain.entity.Page;
import com.dahhong.whoami.reply.adapter.in.dto.CreateReplyResponseDto;
import com.dahhong.whoami.reply.adapter.in.dto.GetReplyResponseDto;
import com.dahhong.whoami.reply.adapter.in.dto.ReplyRequestDto;
import com.dahhong.whoami.reply.application.port.in.CreateReplyUseCase;
import com.dahhong.whoami.reply.application.port.in.GetReplyUseCase;
import com.dahhong.whoami.reply.domain.entity.Reply;
import com.dahhong.whoami.user.application.port.in.GetUserUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/page")
public class PageController implements PageControllerSwagger {

	private final CreatePageUseCase createPageUseCase;

	private final DeletePageUseCase deletePageUseCase;

	private final UpdatePageUseCase updatePageUseCase;

	private final GetPageUseCase getPageUseCase;

	private final GetReplyUseCase getReplyUseCase;

	private final CreateReplyUseCase createReplyUseCase;

	@GetMapping("/{id}")
	public ResponseEntity<?> getPage(@PathVariable Long id) {
		GetPageResponseDto page = GetPageResponseDto.of(getPageUseCase.getPage(id));
		return ResponseEntity.ok(ApiResponse.success(page));
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllPages() {
		List<GetPageResponseDto> allPages = getPageUseCase.getAllPages().stream().map((page)-> GetPageResponseDto.of(page)).toList();
		return ResponseEntity.ok(ApiResponse.success(allPages));
	}

	@PostMapping("/create")
	public ResponseEntity<?> createPage(@Valid @RequestBody PageRequestDto pageRequest, @AuthenticationPrincipal String userId) {
		/*if (userId == null) {
			throw new AuthorizationFailureException("인증 정보가 없습니다.", null);
		}*/
		Page createdPage = createPageUseCase.createPage(userId, pageRequest.getTitle());
		return ResponseEntity.ok(ApiResponse.success(new CreatePageResponseDto("성공적으로 페이지를 생성했습니다", createdPage.getId())));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updatePage(@PathVariable Long id, @Valid @RequestBody PageRequestDto pageRequest, @AuthenticationPrincipal String userId) {
		if (!getPageUseCase.isOwnerOfPage(id, userId)) {
			throw new AuthorizationFailureException("인증 정보가 일치하지 않습니다.", null);
		}
		updatePageUseCase.updatePage(id, userId, pageRequest);
		return ResponseEntity.ok(ApiResponse.success());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePage(@PathVariable Long id, @AuthenticationPrincipal String userId) {
		if (!getPageUseCase.isOwnerOfPage(id, userId)) {
			throw new AuthorizationFailureException("인증 정보가 일치하지 않습니다.", null);
		}
		deletePageUseCase.deletePage(id);
		return ResponseEntity.ok(ApiResponse.success());
	}

	@GetMapping("/all/{userId}")
	public ResponseEntity<?> getPageOfUser(@PathVariable String userId) {
		/* 유저 검증 => GetUserUseCase.getUser(userId)에서 orElseThrow로 NotFoundException 나오게 되어있음 */
		List<GetPageResponseDto> pagesOfUser = getPageUseCase.getPagesOfUser(userId).stream().map((page)-> GetPageResponseDto.of(page)).toList();
		return ResponseEntity.ok(ApiResponse.success(pagesOfUser));
	}

	@PostMapping("/replies/{pageId}")
	public ResponseEntity<?> createReplyToPage(@PathVariable Long pageId, @Valid @RequestBody ReplyRequestDto replyRequest) {
		Reply createdReply = createReplyUseCase.createReply(pageId, replyRequest.getReplyUsername(), replyRequest.getContent());
		return ResponseEntity.ok(ApiResponse.success(new CreateReplyResponseDto("성공적으로 답변을 생성하였습니다.", createdReply.getId())));
	}

	@GetMapping("/replies/{pageId}")
	public ResponseEntity<?> getRepliesOfPage(@PathVariable Long pageId) {
		List<GetReplyResponseDto> replies = getReplyUseCase.getRepliesOfPage(pageId).stream().map((reply)-> GetReplyResponseDto.of(reply, pageId)).toList();
		return ResponseEntity.ok(ApiResponse.success(replies));
	}
}
