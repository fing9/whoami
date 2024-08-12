package com.dahhong.whoami.page.adapter.in.dto;

import com.dahhong.whoami.page.domain.entity.Page;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetPageResponseDto {
	private Long id;
	private String title;
	private String userId;

	public static GetPageResponseDto of (Page page) {
		return new GetPageResponseDto(
				page.getId(),
				page.getTitle(),
				page.getUser().getId()
		);
	}
}
