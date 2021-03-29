package com.cos.costargram.domain.folloew;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FollowRepository extends JpaRepository<Follow, Integer> {
	
	// insert,delete (@Transactional)
	
	// update (@Modifing)
	
	// nativeQuery 짜는법 :붙여주는 것. 어노테이션 꼭 붙여야하는 것
	@Modifying
	@Query(value="INSERT INTO follow(fromUserId,toUserId,createDate) VALUES(:fromUserId,:toUserId,now())",nativeQuery = true)
	int mFollow(int fromUserId,int toUserId); //prepareStatement updateQuery()=> -1 0 1
	
	@Modifying
	@Query(value="DELETE FROM follow WHERE fromUserId=:fromUserId AND toUserId=:toUserId",nativeQuery = true)
	int mUnFollow(int fromUserId,int toUserId);
	
	@Query(value="select count(*) from follow where fromUserId=:principalId and toUserId=:userId",nativeQuery=true)
	int mFollowState(int principalId, int userId);
	
	@Query(value="select count(*) from follow where fromUserId=:userId",nativeQuery=true)
	int mFollowCount(int userId);
}
