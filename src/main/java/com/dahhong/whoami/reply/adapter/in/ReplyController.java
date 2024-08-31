package com.dahhong.whoami.reply.adapter.in;

import com.dahhong.whoami.global.response.ApiResponse;
import com.dahhong.whoami.reply.adapter.in.dto.CreateReplyResponseDto;
import com.dahhong.whoami.reply.adapter.in.dto.GetReplyResponseDto;
import com.dahhong.whoami.reply.adapter.in.dto.ReplyRequestDto;
import com.dahhong.whoami.reply.adapter.in.swagger.ReplyControllerSwagger;
import com.dahhong.whoami.reply.application.port.in.CreateReplyUseCase;
import com.dahhong.whoami.reply.application.port.in.DeleteReplyUseCase;
import com.dahhong.whoami.reply.application.port.in.GetReplyUseCase;
import com.dahhong.whoami.reply.domain.entity.Reply;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reply")
public class ReplyController implements ReplyControllerSwagger {
	private final GetReplyUseCase getReplyUseCase;

	private final DeleteReplyUseCase deleteReplyUseCase;

	@GetMapping("/all")
	public ResponseEntity<?> getAllReplies() {
		List<GetReplyResponseDto> allReplies = getReplyUseCase.getAllReplies().stream().map((reply)-> GetReplyResponseDto.of(reply)).toList();
		return ResponseEntity.ok(ApiResponse.success(allReplies));
	}

	@DeleteMapping("/{replyId}")
	public ResponseEntity<?> deleteReply(@Positive @PathVariable Long replyId) {
		//Security Filter에서 Admin 여부가 확인됨
		deleteReplyUseCase.deleteReply(replyId);
		return ResponseEntity.ok(ApiResponse.success());
	}
}
