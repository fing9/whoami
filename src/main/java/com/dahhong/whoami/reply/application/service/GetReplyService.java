package com.dahhong.whoami.reply.application.service;

import com.dahhong.whoami.global.exception.customException.NotFoundException;
import com.dahhong.whoami.page.application.port.in.GetPageUseCase;
import com.dahhong.whoami.reply.application.port.in.GetReplyUseCase;
import com.dahhong.whoami.reply.application.port.out.ReplyQueryPort;
import com.dahhong.whoami.reply.domain.entity.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetReplyService implements GetReplyUseCase {
	private final ReplyQueryPort replyQueryPort;

	private final GetPageUseCase getPageUseCase;

	@Override
	public List<Reply> getAllReplies() {
		return replyQueryPort.findAllFetchJoin();
	}

	@Override
	public List<Reply> getRepliesOfPage(Long pageId) {
		return replyQueryPort.findByPageIdFetchJoin(pageId);
	}

	@Override
	public Reply getReplyById(Long id) {
		return replyQueryPort.findById(id).orElseThrow(()-> new NotFoundException("해당 답변을 찾을 수 없습니다."));
	}
}
