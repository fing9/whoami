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

	private final GetPageUseCase getPageUseCase;

	@Override
	@Transactional
	public void updatePage(Long id, String userId, PageRequestDto pageDetails) {
		User user = getUserUseCase.getUser(userId);
		Page page = getPageUseCase.getPage(id); //없으면 orElseThrow됨 내부에서
		pageCommandPort.save(Page.of(page.getId(), user, pageDetails.getTitle()));
	}
}
