package com.dahhong.whoami.page.application.service;

import com.dahhong.whoami.global.exception.customException.NotFoundException;
import com.dahhong.whoami.page.application.port.in.GetPageUseCase;
import com.dahhong.whoami.page.application.port.out.PageQueryPort;
import com.dahhong.whoami.page.domain.entity.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetPageService implements GetPageUseCase {
	private final PageQueryPort pageQueryPort;

	@Override
	public Page getPage(Long id) {
		return pageQueryPort.findById(id).orElseThrow(()-> new NotFoundException("해당 페이지를 찾을 수 없습니다."));
	}

	@Override
	public List<Page> getAllPages() {
		return pageQueryPort.findAll();
	}
}
