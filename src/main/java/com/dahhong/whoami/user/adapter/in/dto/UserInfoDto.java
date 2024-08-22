package com.dahhong.whoami.user.adapter.in.dto;

import com.dahhong.whoami.user.domain.entity.AuthType;
import com.dahhong.whoami.user.domain.entity.Role;
import com.dahhong.whoami.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfoDto {
	private String id;
	private Role role;
	private AuthType authType;
	private String name;
	private String profilePicture;

	public static UserInfoDto of(User user) {
		return new UserInfoDto(
				user.getId(),
				user.getRole(),
				user.getAuthType(),
				user.getName(),
				user.getProfilePicture()
		);
	}
}
