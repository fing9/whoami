package com.dahhong.whoami.page.infrastructure.repository;

import com.dahhong.whoami.page.domain.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PageRepository extends JpaRepository<Page, Long> {

	@Query("select p from Page p join fetch p.user")
	List<Page> findAllJoinFetch();

	@Query("select p from Page p join fetch p.user u where u.id = :userId")
	List<Page> findByUserIdJoinFetch(String userId);
}
