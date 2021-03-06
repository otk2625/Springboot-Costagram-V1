package com.cos.costargram.web;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.costargram.config.auth.PrincipalDetails;
import com.cos.costargram.domain.folloew.Follow;
import com.cos.costargram.service.FollowService;
import com.cos.costargram.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class FollowController {
	
	private final FollowService followService;
	
	@PostMapping("/follow/{toUserId}")
	public CMRespDto<?> follow(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int toUserId) {
		int result =  followService.팔로우(principalDetails.getUser().getId(), toUserId);
		return new CMRespDto<>(1, result);
	}
	
	@DeleteMapping("/follow/{toUserId}")
	public CMRespDto<?> unFollow(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int toUserId) {
		int result =  followService.언팔로우(principalDetails.getUser().getId(), toUserId);
		return new CMRespDto<>(1, result);
	}
}
