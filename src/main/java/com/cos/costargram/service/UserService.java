package com.cos.costargram.service;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.costargram.domain.folloew.FollowRepository;
import com.cos.costargram.domain.user.User;
import com.cos.costargram.domain.user.UserRepository;
import com.cos.costargram.web.dto.user.UserProfileRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;
	private final FollowRepository followRepository;
	
	@Transactional(readOnly = true) 
	public UserProfileRespDto 회원프로필(int userId, int principalId) throws IllegalAccessException {
		UserProfileRespDto userProfileRespDto = new UserProfileRespDto();		
		
		User userEntity = userRepository.findById(userId).orElseThrow(() -> {
			return new IllegalAccessException();
		});
		
		int followState = followRepository.mFollowState(principalId, userId);
		int followCount = followRepository.mFollowCount(userId);
		
		
		userProfileRespDto.setFollowState(followState == 1);
		userProfileRespDto.setUser(userEntity);
		userProfileRespDto.setFollowCount(followCount); //내가 팔로우 하고 있는 카운트
		userProfileRespDto.setImageCount(10);
		
		return userProfileRespDto;
		
	}
}
