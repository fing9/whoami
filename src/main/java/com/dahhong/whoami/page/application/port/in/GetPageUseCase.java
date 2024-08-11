package com.dahhong.whoami.page.application.port.in;

import com.dahhong.whoami.page.domain.entity.Page;

import java.util.List;

public interface GetPageUseCase {
	Page getPage(Long id);

	List<Page> getAllPages();

	boolean isOwnerOfPage(Long id, String userId);
}
