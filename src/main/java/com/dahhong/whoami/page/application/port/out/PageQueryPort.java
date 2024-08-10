package com.dahhong.whoami.page.application.port.out;

import com.dahhong.whoami.page.domain.entity.Page;

import java.util.Optional;

public interface PageQueryPort {

	Optional<Page> findById(Long pageId);
}
