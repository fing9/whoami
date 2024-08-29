package com.dahhong.whoami.reply.application.port.in;

import com.dahhong.whoami.reply.domain.entity.Reply;

public interface DeleteReplyUseCase {
	public void deleteReply(Long id);
}
