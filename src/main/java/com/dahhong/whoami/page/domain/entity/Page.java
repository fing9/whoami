package com.dahhong.whoami.page.domain.entity;

import com.dahhong.whoami.global.auditing.BaseTimeEntity;
import com.dahhong.whoami.user.domain.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name="wai_page")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Page extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	@Column(length = 100)
	private String title;

	public static Page of(Long id, User user, String title) {
		return new Page(id, user, title);
	}
}
