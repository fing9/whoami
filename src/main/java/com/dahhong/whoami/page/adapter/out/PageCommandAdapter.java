package com.dahhong.whoami.page.adapter.out;

import com.dahhong.whoami.page.application.port.out.PageCommandPort;
import com.dahhong.whoami.page.domain.entity.Page;
import com.dahhong.whoami.page.infrastructure.repository.PageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PageCommandAdapter implements PageCommandPort {

	private final PageRepository pageRepository;

	@Override
	public Page save(Page page) {
		return pageRepository.save(page);
	}

	@Override
	public void delete(Page page) {
		pageRepository.delete(page);
	}
}
