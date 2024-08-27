package com.dahhong.whoami.reply.application.service;

import com.dahhong.whoami.page.application.port.in.GetPageUseCase;
import com.dahhong.whoami.page.domain.entity.Page;
import com.dahhong.whoami.reply.application.port.in.CreateReplyUseCase;
import com.dahhong.whoami.reply.application.port.out.ReplyCommandPort;
import com.dahhong.whoami.reply.domain.entity.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateReplyService implements CreateReplyUseCase {
	private final ReplyCommandPort replyCommandPort;

	private final GetPageUseCase getPageUseCase;

	@Override
	public Reply createReply(Long pageId, String replyUsername, String content) {
		Page page = getPageUseCase.getPage(pageId);
		Reply reply = Reply.of(null, replyUsername, page, content);
		return replyCommandPort.save(reply);
	}
}
