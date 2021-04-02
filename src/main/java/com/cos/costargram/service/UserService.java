package com.cos.costargram.service;



import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional(readOnly = true) 
	public UserProfileRespDto 회원프로필(int userId, int principalId) throws IllegalAccessException {
		UserProfileRespDto userProfileRespDto = new UserProfileRespDto();		
		
		User userEntity = userRepository.findById(userId).orElseThrow(() -> {
			return new IllegalAccessException();
		});
		
		int followState = followRepository.mFollowState(principalId, userId);
		int followCount = followRepository.mFollowCount(userId);
		
		
		userProfileRespDto.setFollowState(followState == 1);
		userProfileRespDto.setFollowCount(followCount); //내가 팔로우 하고 있는 카운트
		userProfileRespDto.setImageCount(userEntity.getImages().size());
		
		userEntity.getImages().forEach((image)->{
			image.setLikeCount(image.getLikes().size());
		});
		
		userProfileRespDto.setUser(userEntity);
		
		return userProfileRespDto;
		
	}

	@Transactional
	public User 회원수정(int id, User user) {
		User userEntity = userRepository.findById(id).get();
		
		//더티체킹
		userEntity.setName(user.getName());
		userEntity.setBio(user.getBio());
		userEntity.setWebSite(user.getWebSite());
		userEntity.setGender(user.getGender());
		
		String rawPassword=user.getPassword();
		String encPassword=bCryptPasswordEncoder.encode(rawPassword);
		userEntity.setPassword(encPassword);
		
		return userEntity;
	}
}
