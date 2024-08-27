package com.dahhong.whoami.reply.domain.entity;

import com.dahhong.whoami.global.auditing.BaseTimeEntity;
import com.dahhong.whoami.page.domain.entity.Page;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "wai_reply")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Reply extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String replyUsername;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "page_id")
	private Page page;

	@Column(length = 500)
	private String content;
}
