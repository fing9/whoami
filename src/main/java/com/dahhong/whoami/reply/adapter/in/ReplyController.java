package com.dahhong.whoami.reply.adapter.in;

import com.dahhong.whoami.global.response.ApiResponse;
import com.dahhong.whoami.reply.adapter.in.dto.GetReplyResponseDto;
import com.dahhong.whoami.reply.adapter.in.dto.ReplyRequestDto;
import com.dahhong.whoami.reply.application.port.in.CreateReplyUseCase;
import com.dahhong.whoami.reply.application.port.in.DeleteReplyUseCase;
import com.dahhong.whoami.reply.application.port.in.GetReplyUseCase;
import com.dahhong.whoami.reply.application.port.in.UpdateReplyUseCase;
import com.dahhong.whoami.reply.domain.entity.Reply;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reply")
public class ReplyController {

	private final CreateReplyUseCase createReplyUseCase;

	private final GetReplyUseCase getReplyUseCase;

	@PostMapping("/{pageId}")
	public ResponseEntity<?> createReply(@PathVariable Long pageId, @Valid @RequestBody ReplyRequestDto replyRequest) {
		Reply createdReply = createReplyUseCase.createReply(pageId, replyRequest.getReplyUsername(), replyRequest.getContent());
		return ResponseEntity.ok(ApiResponse.success(createdReply)); //CreateReplyResponseDto 굳이 필요할까요? 안 넣겠습니다.
	}

	@GetMapping("/{pageId}")
	public ResponseEntity<?> getReplyOfPage(@PathVariable Long pageId) {
		List<GetReplyResponseDto> replies = getReplyUseCase.getRepliesOfPage(pageId).stream().map((reply)-> GetReplyResponseDto.of(reply)).toList();
		return ResponseEntity.ok(ApiResponse.success(replies));
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllReplys() {
		List<GetReplyResponseDto> allReplies = getReplyUseCase.getAllReplies().stream().map((reply)-> GetReplyResponseDto.of(reply)).toList();
		return ResponseEntity.ok(ApiResponse.success(allReplies));
	}
}
