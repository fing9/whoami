package com.dahhong.whoami.reply.application.port.in;

import com.dahhong.whoami.reply.domain.entity.Reply;

public interface GetReplyUseCase {
	Reply getReplyOfPage(Long pageId);
}
