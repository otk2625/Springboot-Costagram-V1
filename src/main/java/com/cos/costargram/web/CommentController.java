package com.cos.costargram.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.costargram.config.auth.PrincipalDetails;
import com.cos.costargram.service.CommentService;
import com.cos.costargram.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CommentController {
	
	private final CommentService commentService;
	
	@DeleteMapping("/comment/{commentId}")
	public @ResponseBody CMRespDto<?> delete(@PathVariable int commentId, @AuthenticationPrincipal PrincipalDetails principalDetails){   // content, imageId, userId(세션)
		
		
		commentService.댓글삭제(commentId, principalDetails.getUser().getId());
		
		return new CMRespDto<>(1, null);
	}
}
