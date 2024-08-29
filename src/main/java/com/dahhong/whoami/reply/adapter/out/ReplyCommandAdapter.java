package com.dahhong.whoami.reply.adapter.out;

import com.dahhong.whoami.reply.application.port.out.ReplyCommandPort;
import com.dahhong.whoami.reply.domain.entity.Reply;
import com.dahhong.whoami.reply.infrastructure.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReplyCommandAdapter implements ReplyCommandPort {
	private final ReplyRepository replyRepository;

	@Override
	public Reply save(Reply reply) {
		return replyRepository.save(reply);
	}

	@Override
	public void delete(Reply reply) {
		replyRepository.delete(reply);
	}
}
