package com.dahhong.whoami.page.application.port.in;

import com.dahhong.whoami.page.domain.entity.Page;

public interface CreatePageUseCase {
	void createPage(String userId, String title);
}
