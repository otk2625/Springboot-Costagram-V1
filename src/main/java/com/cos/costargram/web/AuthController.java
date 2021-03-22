package com.cos.costargram.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 시작주소: /auth
@Controller
public class AuthController {
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		
		return "auth/loginForm"; //auth앞에 /안붙여도 됨
	}
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		
		return "auth/joinForm"; //auth앞에 /안붙여도 됨
	}
}
