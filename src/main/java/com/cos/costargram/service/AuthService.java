package com.cos.costargram.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cos.costargram.domain.user.RoleType;
import com.cos.costargram.domain.user.User;
import com.cos.costargram.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User join(User user) {
		// 패스워드 암호화 로직
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		
		user.setRole(RoleType.USER); //컨트롤러에 넣지 말 것!
		
		return userRepository.save(user);
	}
}
