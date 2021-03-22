package com.cos.costargram.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ImageController {
	
	@GetMapping({"/","/image/feed"})
	public String feed() {
		return "image/feed";
	}
	
	@GetMapping("/image/explor")
	public String explore() {
		return "image/explor";
	}
	
	@GetMapping("/image/upload")
	public String upload() {
		return "image/imageUpload";
	}
}
