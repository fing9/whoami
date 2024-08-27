package com.dahhong.whoami.reply.application.port.out;

import com.dahhong.whoami.reply.domain.entity.Reply;

public interface ReplyCommandPort {
	Reply save (Reply reply);
}
