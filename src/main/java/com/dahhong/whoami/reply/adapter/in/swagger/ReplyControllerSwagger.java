package com.dahhong.whoami.reply.adapter.in.swagger;

import com.dahhong.whoami.global.response.ApiResponse;
import com.dahhong.whoami.reply.adapter.in.dto.CreateReplyResponseDto;
import com.dahhong.whoami.reply.adapter.in.dto.GetReplyResponseDto;
import com.dahhong.whoami.reply.adapter.in.dto.ReplyRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "답변", description = "답변 관련 API")
public interface ReplyControllerSwagger {

	@Operation(summary = "답변 생성", description = "특정 페이지에 답변을 생성합니다.")
	@Parameters({
			@Parameter(name = "pageId", description = "답변을 추가할 페이지의 ID", required = true)
	})
	ResponseEntity<?> createReply(@PathVariable Long pageId, @RequestBody @Valid ReplyRequestDto replyRequest);

	@Operation(summary = "특정 페이지의 답변 조회", description = "특정 페이지에 대한 모든 답변을 조회합니다.")
	@Parameters({
			@Parameter(name = "pageId", description = "답변을 조회할 페이지의 ID", required = true)
	})
	ResponseEntity<?> getReplyOfPage(@PathVariable Long pageId);

	@Operation(summary = "모든 답변 조회", description = "모든 답변을 조회합니다.")
	ResponseEntity<?> getAllReplies();
}
