package com.dahhong.whoami.page.application.service;

import com.dahhong.whoami.page.application.port.in.CreatePageUseCase;
import com.dahhong.whoami.page.application.port.out.PageCommandPort;
import com.dahhong.whoami.page.application.port.out.PageQueryPort;
import com.dahhong.whoami.page.domain.entity.Page;
import com.dahhong.whoami.user.application.port.in.GetUserUseCase;
import com.dahhong.whoami.user.domain.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePageService implements CreatePageUseCase {
	private final PageCommandPort pageCommandPort;

	private final GetUserUseCase getUserUseCase;

	@Override
	@Transactional
	public Page createPage(String userId, String title) {
		User user = getUserUseCase.getUser(userId);
		Page page = Page.of(null, user, title); //id는 아직 없으니 null로 퉁칩니다
		return pageCommandPort.save(page);
	}
}
