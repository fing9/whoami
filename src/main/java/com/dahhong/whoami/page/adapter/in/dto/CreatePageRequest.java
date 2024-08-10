package com.dahhong.whoami.page.adapter.in.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CreatePageRequest {

	@NotBlank(message = "User Id Is Required")
	String userId;

	@NotBlank(message = "Title Is Required")
	String title;
}
