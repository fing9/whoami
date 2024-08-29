package com.dahhong.whoami.reply.application.service;

import com.dahhong.whoami.reply.application.port.in.DeleteReplyUseCase;
import com.dahhong.whoami.reply.application.port.in.GetReplyUseCase;
import com.dahhong.whoami.reply.application.port.out.ReplyCommandPort;
import com.dahhong.whoami.reply.domain.entity.Reply;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteReplyService implements DeleteReplyUseCase {
	private final ReplyCommandPort replyCommandPort;

	private final GetReplyUseCase getReplyUseCase;

	@Override
	@Transactional
	public void deleteReply(Long id) {
		Reply reply = getReplyUseCase.getReplyById(id);
		replyCommandPort.delete(reply);
	}
}
