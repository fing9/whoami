package com.dahhong.whoami.reply.adapter.in.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CreateReplyResponseDto {
	private final String message;
	private final Long id;
}
