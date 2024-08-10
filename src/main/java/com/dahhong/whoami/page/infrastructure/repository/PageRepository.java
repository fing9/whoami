package com.dahhong.whoami.page.infrastructure.repository;

import com.dahhong.whoami.page.domain.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PageRepository extends JpaRepository<Page, Long> {
}
