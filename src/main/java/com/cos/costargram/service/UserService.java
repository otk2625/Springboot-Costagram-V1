package com.cos.costargram.service;



import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cos.costargram.config.auth.PrincipalDetails;
import com.cos.costargram.domain.folloew.FollowRepository;
import com.cos.costargram.domain.image.Image;
import com.cos.costargram.domain.tag.Tag;
import com.cos.costargram.domain.user.User;
import com.cos.costargram.domain.user.UserRepository;
import com.cos.costargram.utils.TagUtils;
import com.cos.costargram.web.dto.image.ImageReqDto;
import com.cos.costargram.web.dto.user.UserProfileRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;
	private final FollowRepository followRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Value("${file.path}") // application.yml 파일에 접근가능
	private String uploadFolder;
	
	@Transactional
	public User 회원사진변경(MultipartFile profileImageFile, PrincipalDetails principalDetails) {
		
		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid + "_" +profileImageFile.getOriginalFilename();
		//System.out.println("파일명 : " + imageFileName);
		
		Path inageFilePath = Paths.get(uploadFolder + imageFileName);
		//System.out.println("파일패스 : " + inageFilePath);
		
		try {
			Files.write(inageFilePath, profileImageFile.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		User userEntity = userRepository.findById(principalDetails.getUser().getId()).get();
		userEntity.setProfileImageUrl(imageFileName);
		
		return userEntity;
	}//더티체킹
	
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
