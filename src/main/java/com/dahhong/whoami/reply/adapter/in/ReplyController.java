package com.dahhong.whoami.reply.adapter.in;

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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reply")
public class ReplyController {

	//private final CreateReplyUseCase createReplyUseCase;

	//private final GetReplyUseCase getReplyUseCase;

	@PostMapping("/create")
	public ResponseEntity<?> createReply(@Valid @RequestBody ReplyRequestDto replyRequest) {
		/**
		 * TODO : createReplyUseCase.save()
		 */
		return null;
	}

	@GetMapping("/{pageId}")
	public ResponseEntity<?> getReplyOfPage(@PathVariable int pageId) {
		/**
		 * TODO : getReplyUseCase.getReplyOfPage()
		 */
		return null;
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllReplys() {
		/**
		 * TODO : getReplyUseCase.getAllReplies()
		 */
		return null;
	}
}
