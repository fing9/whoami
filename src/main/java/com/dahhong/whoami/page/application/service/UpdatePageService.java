package com.dahhong.whoami.page.application.service;

import com.dahhong.whoami.page.adapter.in.dto.PageRequestDto;
import com.dahhong.whoami.page.application.port.in.GetPageUseCase;
import com.dahhong.whoami.page.application.port.in.UpdatePageUseCase;
import com.dahhong.whoami.page.application.port.out.PageCommandPort;
import com.dahhong.whoami.page.domain.entity.Page;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatePageService implements UpdatePageUseCase {

	private final PageCommandPort pageCommandPort;

	@Override
	@Transactional
	public void updatePage(Long id, PageRequestDto pageDetails) {
		pageCommandPort.save(Page.of(id, pageDetails.getUserId(), pageDetails.getTitle()));
	}
}
