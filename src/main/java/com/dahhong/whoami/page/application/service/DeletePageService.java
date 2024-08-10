package com.dahhong.whoami.page.application.service;

import com.dahhong.whoami.page.application.port.in.DeletePageUseCase;
import com.dahhong.whoami.page.application.port.in.GetPageUseCase;
import com.dahhong.whoami.page.application.port.out.PageCommandPort;
import com.dahhong.whoami.page.domain.entity.Page;
import com.dahhong.whoami.user.application.port.in.GetUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletePageService implements DeletePageUseCase {

	private final PageCommandPort pageCommandPort;

	private final GetPageUseCase getPageUseCase;

	@Override
	public void deletePage(Long id) {
		Page page = getPageUseCase.getPage(id);
		pageCommandPort.delete(page);
	}
}
