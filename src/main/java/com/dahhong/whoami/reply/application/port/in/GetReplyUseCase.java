package com.dahhong.whoami.reply.application.port.in;

import com.dahhong.whoami.reply.domain.entity.Reply;

import java.util.List;
import java.util.Optional;

public interface GetReplyUseCase {
	List<Reply> getAllReplies();

	List<Reply> getRepliesOfPage(Long pageId);

	Reply getReplyById(Long id);
}
