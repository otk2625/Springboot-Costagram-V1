package com.cos.costargram.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.costargram.domain.user.User;
import com.cos.costargram.service.AuthService;
import com.cos.costargram.utils.Script;
import com.cos.costargram.web.dto.UserJoinReqDto;

import lombok.RequiredArgsConstructor;

// 시작주소: /auth
@RequiredArgsConstructor
@Controller
public class AuthController {
	private final AuthService authService;
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		
		return "auth/loginForm"; //auth앞에 /안붙여도 됨
	}
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		
		return "auth/joinForm"; //auth앞에 /안붙여도 됨
	}
	
	@PostMapping("/auth/join")
	public @ResponseBody String join(UserJoinReqDto userJoinReqDto) { //@RequestBody안적어도 됨! 스프링부트에서 알아서 해줌, 적으면 ContextType에러뜸
		User userEntity = userJoinReqDto.toEntity();
		authService.join(userEntity);
		
		return Script.href("회원가입 완료", "/auth/loginForm"); // 리다이렉션으로 가게해야함!
	}
}
