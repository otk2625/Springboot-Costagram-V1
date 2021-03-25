package com.cos.costargram.service;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.costargram.domain.user.User;
import com.cos.costargram.domain.user.UserRepository;
import com.cos.costargram.web.dto.user.UserProfileRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;
	
	@Transactional(readOnly = true) 
	public UserProfileRespDto 회원프로필(int userId, int principalId) throws IllegalAccessException {
		UserProfileRespDto userProfileRespDto = new UserProfileRespDto();		
		
		User userEntity = userRepository.findById(userId).orElseThrow(() -> {
			return new IllegalAccessException();
		});
		userProfileRespDto.setFollowState(true);
		userProfileRespDto.setUser(userEntity);
		userProfileRespDto.setFollowCount(100);
		userProfileRespDto.setImageCount(10);
		
		return userProfileRespDto;
		
	}
}
