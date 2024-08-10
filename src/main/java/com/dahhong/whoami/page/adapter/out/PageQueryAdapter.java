package com.dahhong.whoami.page.adapter.out;

import com.dahhong.whoami.page.application.port.out.PageQueryPort;
import com.dahhong.whoami.page.domain.entity.Page;
import com.dahhong.whoami.page.infrastructure.repository.PageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PageQueryAdapter implements PageQueryPort {

	private final PageRepository pageRepository;

	@Override
	public Optional<Page> findById(Long pageId) {
		return pageRepository.findById(pageId);
	}
}
