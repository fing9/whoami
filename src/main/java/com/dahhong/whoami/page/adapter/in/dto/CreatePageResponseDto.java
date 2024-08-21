package com.dahhong.whoami.page.adapter.in.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CreatePageResponseDto {
	private final String message;
	private final Long id;
}
