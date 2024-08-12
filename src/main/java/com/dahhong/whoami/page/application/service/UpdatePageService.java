package com.dahhong.whoami.page.application.service;

import com.dahhong.whoami.page.adapter.in.dto.PageRequestDto;
import com.dahhong.whoami.page.application.port.in.GetPageUseCase;
import com.dahhong.whoami.page.application.port.in.UpdatePageUseCase;
import com.dahhong.whoami.page.application.port.out.PageCommandPort;
import com.dahhong.whoami.page.domain.entity.Page;
import com.dahhong.whoami.user.application.port.in.GetUserUseCase;
import com.dahhong.whoami.user.domain.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatePageService implements UpdatePageUseCase {

	private final PageCommandPort pageCommandPort;

	private final GetUserUseCase getUserUseCase;

	@Override
	@Transactional
	public void updatePage(Long id, String userId, PageRequestDto pageDetails) {
		User user = getUserUseCase.getUser(userId);
		pageCommandPort.save(Page.of(id, user, pageDetails.getTitle()));
	}
}
