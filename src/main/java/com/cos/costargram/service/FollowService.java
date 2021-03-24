package com.cos.costargram.service;

import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cos.costargram.domain.folloew.Follow;
import com.cos.costargram.domain.folloew.FollowRepository;
import com.cos.costargram.domain.user.RoleType;
import com.cos.costargram.domain.user.User;
import com.cos.costargram.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FollowService {
	private final FollowRepository followRepository;
	
	@Transactional
	public int 팔로우(int formUserId, int toUserId) {
		return followRepository.mFollow(formUserId, toUserId);
	}
	
	@Transactional
	public int 언팔로우(int formUserId, int toUserId) {
		return followRepository.mUnFollow(formUserId, toUserId);
	}
}
