package com.cos.costargram.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.costargram.service.LikesService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class LikeController {
	private LikesService likesService;
	
	
}
