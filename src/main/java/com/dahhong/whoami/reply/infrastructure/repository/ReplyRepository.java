package com.dahhong.whoami.reply.infrastructure.repository;

import com.dahhong.whoami.reply.domain.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

	@Query("select r from Reply r join fetch r.page p")
	List<Reply> findAllJoinFetch();

	@Query("select r from Reply r join fetch r.page p where p.id = :pageId")
	List<Reply> findByPageIdFetchJoin(Long pageId);
}
