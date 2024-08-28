package com.dahhong.whoami.reply.adapter.in;

import com.dahhong.whoami.global.response.ApiResponse;
import com.dahhong.whoami.reply.adapter.in.dto.CreateReplyResponseDto;
import com.dahhong.whoami.reply.adapter.in.dto.GetReplyResponseDto;
import com.dahhong.whoami.reply.adapter.in.dto.ReplyRequestDto;
import com.dahhong.whoami.reply.adapter.in.swagger.ReplyControllerSwagger;
import com.dahhong.whoami.reply.application.port.in.CreateReplyUseCase;
import com.dahhong.whoami.reply.application.port.in.GetReplyUseCase;
import com.dahhong.whoami.reply.domain.entity.Reply;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reply")
public class ReplyController implements ReplyControllerSwagger {

	private final CreateReplyUseCase createReplyUseCase;

	private final GetReplyUseCase getReplyUseCase;

	@PostMapping("/{pageId}")
	public ResponseEntity<?> createReply(@PathVariable Long pageId, @Valid @RequestBody ReplyRequestDto replyRequest) {
		Reply createdReply = createReplyUseCase.createReply(pageId, replyRequest.getReplyUsername(), replyRequest.getContent());
		return ResponseEntity.ok(ApiResponse.success(new CreateReplyResponseDto("성공적으로 답변을 생성하였습니다.", createdReply.getId())));
	}

	@GetMapping("/{pageId}")
	public ResponseEntity<?> getReplyOfPage(@PathVariable Long pageId) {
		List<GetReplyResponseDto> replies = getReplyUseCase.getRepliesOfPage(pageId).stream().map((reply)-> GetReplyResponseDto.of(reply, pageId)).toList();
		return ResponseEntity.ok(ApiResponse.success(replies));
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllReplies() {
		List<GetReplyResponseDto> allReplies = getReplyUseCase.getAllReplies().stream().map((reply)-> GetReplyResponseDto.of(reply)).toList();
		return ResponseEntity.ok(ApiResponse.success(allReplies));
	}
}
