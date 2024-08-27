package com.dahhong.whoami.reply.adapter.in.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ReplyRequestDto {
	@NotBlank(message = "Username of reply is required")
	private String replyUsername;

	@NotBlank(message = "content is required")
	private String content;
}
