package com.dahhong.whoami.reply.adapter.out;

import com.dahhong.whoami.reply.application.port.out.ReplyQueryPort;
import com.dahhong.whoami.reply.domain.entity.Reply;
import com.dahhong.whoami.reply.infrastructure.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReplyQueryAdapter implements ReplyQueryPort {

	private final ReplyRepository replyRepository;

	@Override
	public List<Reply> findAllFetchJoin() {
		return replyRepository.findAllJoinFetch();
	}

	@Override
	public List<Reply> findByPageIdFetchJoin(Long pageId) {
		return replyRepository.findByPageIdFetchJoin(pageId);
	}

	@Override
	public Optional<Reply> findById(Long id) {
		return replyRepository.findById(id);
	}
}
