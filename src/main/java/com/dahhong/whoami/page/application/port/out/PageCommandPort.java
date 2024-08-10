package com.dahhong.whoami.page.application.port.out;

import com.dahhong.whoami.page.domain.entity.Page;

public interface PageCommandPort {

	Page save(Page page);

	void delete(Page page);
}
