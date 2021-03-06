package com.cos.costargram.domain.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LikesRepository extends JpaRepository<Likes, Integer> {

	
	@Modifying
	@Query(value = "INSERT INTO likes(imageId, userId) VALUES(:imageId, :principalId)", nativeQuery = true)
	int mLike(int imageId, int principalId);

	@Modifying
	@Query(value = "DELETE FROM likes WHERE imageId = :imageId AND userId = :principalId", nativeQuery = true)
	int mdelete(int imageId, int principalId);

}
