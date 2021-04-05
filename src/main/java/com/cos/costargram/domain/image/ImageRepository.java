package com.cos.costargram.domain.image;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.costargram.domain.image.query.ImageQuery;

public interface ImageRepository extends JpaRepository<Image, Integer> {
	
	@Query(value = "select * from image where userId in (select toUserId from follow where fromUserId = :principalId) order by id desc ", nativeQuery = true)
	Page<Image> mFeeds(int principalId, Pageable page); //이렇게만 해줘도 페이징 처리가 된다
	
	
	@Query(value = "select * from image where id in (select imageId from (select imageId, count(imageId) likeCount from likes group by imageId order by 2 desc) t) and userId != :principalId  ", nativeQuery = true)
	List<Image> mExplore(int principalId);

}
