package com.dahhong.whoami.reply.adapter.in.dto;

import com.dahhong.whoami.reply.domain.entity.Reply;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetReplyResponseDto {
	private Long id;
	private String username;
	private String content;
	private Long pageId;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;

	public static GetReplyResponseDto of(Reply reply) {
		return new GetReplyResponseDto(
				reply.getId(),
				reply.getReplyUsername(),
				reply.getContent(),
				reply.getPage().getId(),
				reply.getCreatedDate(),
				reply.getModifiedDate()
		);
	}

	public static GetReplyResponseDto of(Reply reply, Long pageId) {
		return new GetReplyResponseDto(
				reply.getId(),
				reply.getReplyUsername(),
				reply.getContent(),
				pageId,
				reply.getCreatedDate(),
				reply.getModifiedDate()
		);
	}
}
