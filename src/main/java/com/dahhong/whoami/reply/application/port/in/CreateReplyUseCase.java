package com.dahhong.whoami.reply.application.port.in;

import com.dahhong.whoami.reply.domain.entity.Reply;

public interface CreateReplyUseCase {
	Reply createReply(String replyUserId, String content);
}
