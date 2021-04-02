package com.cos.costargram.web;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.costargram.config.auth.PrincipalDetails;
import com.cos.costargram.domain.folloew.Follow;
import com.cos.costargram.domain.user.User;
import com.cos.costargram.service.FollowService;
import com.cos.costargram.service.UserService;
import com.cos.costargram.web.dto.CMRespDto;
import com.cos.costargram.web.dto.follow.FollowRespDto;
import com.cos.costargram.web.dto.user.UserProfileRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {
	
	private final UserService userService;
	private final FollowService followService;
	
	
	@GetMapping("/user/{pageUserId}/follow") //data리턴하는것
	public @ResponseBody CMRespDto<?> followList(@AuthenticationPrincipal PrincipalDetails principalDetails,@PathVariable int pageUserId) {
		
		List<FollowRespDto> followRespDtos = followService.팔로우리스트(principalDetails.getUser().getId(), pageUserId);
		
		return new CMRespDto<>(1, followRespDtos);
	}
	
	
	@GetMapping("/user/{id}")
	public String profile(@PathVariable int id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) throws IllegalAccessException {
		
		UserProfileRespDto profileRespDto = userService.회원프로필( id,principalDetails.getUser().getId());
		model.addAttribute("dto", profileRespDto);
		
		
		return "user/profile";
	}
	
	@GetMapping("/user/{id}/profileSetting")
	public String profileSetting(@PathVariable int id) {
		
		return "user/profileSetting";
	}
	
	@PutMapping("/user/{id}")
	public @ResponseBody CMRespDto<?> profileUpdate(@PathVariable int id, User user, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		User UserEntity = userService.회원수정(id, user);
		
		System.out.println("수정 클릭");
		
		principalDetails.setUser(UserEntity);
		return new CMRespDto<>(1, null);
	}
}
