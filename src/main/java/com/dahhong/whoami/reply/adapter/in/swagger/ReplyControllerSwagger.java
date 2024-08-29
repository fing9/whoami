package com.dahhong.whoami.reply.adapter.in.swagger;

import com.dahhong.whoami.reply.adapter.in.dto.ReplyRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "답변", description = "답변 관련 API")
public interface ReplyControllerSwagger {
	@Operation(summary = "모든 답변 조회", description = "모든 답변을 조회합니다.")
	ResponseEntity<?> getAllReplies();

	@Operation(summary = "특정 답변 삭제", description = "특정 답변을 삭제합니다.")
	ResponseEntity<?> deleteReply(@Positive @PathVariable Long replyId);
}
