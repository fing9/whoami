package com.dahhong.whoami.page.application.port.in;

import com.dahhong.whoami.page.adapter.in.dto.PageRequestDto;
import com.dahhong.whoami.page.domain.entity.Page;

public interface UpdatePageUseCase {
	void updatePage(Long id, String userId, PageRequestDto pageDetails);
}
