package com.dahhong.whoami.page.application.service;

import com.dahhong.whoami.page.application.port.in.CreatePageUseCase;
import com.dahhong.whoami.page.application.port.out.PageCommandPort;
import com.dahhong.whoami.page.application.port.out.PageQueryPort;
import com.dahhong.whoami.page.domain.entity.Page;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePageService implements CreatePageUseCase {
	private final PageCommandPort pageCommandPort;

	@Override
	@Transactional
	public Page createPage(String userId, String title) {
		Page page = Page.of(userId, title);
		return pageCommandPort.save(page);
	}
}
