package com.dahhong.whoami.reply.application.port.out;

import com.dahhong.whoami.reply.domain.entity.Reply;

import java.util.List;

public interface ReplyQueryPort {
	List<Reply> findAllFetchJoin();

	List<Reply> findByPageIdFetchJoin(Long pageId);
}
