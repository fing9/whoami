package com.dahhong.whoami.page.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name="wai_page")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Page {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String userId;

	@Column
	private String title;

	public static Page of(String userId, String title) {
		return new Page().builder().userId(userId).title(title).build();
	}
}
