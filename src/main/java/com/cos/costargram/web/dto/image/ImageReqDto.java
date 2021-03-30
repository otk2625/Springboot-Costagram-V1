package com.cos.costargram.web.dto.image;

import javax.mail.Multipart;

import org.springframework.web.multipart.MultipartFile;

import com.cos.costargram.domain.image.Image;
import com.cos.costargram.domain.user.User;

import lombok.Data;

@Data
public class ImageReqDto {
	private MultipartFile file;
	private String tags; //#태그1, #태그2
	private String caption;
	
	public Image toEntity(String postImageUrl, User userEntity) {
		return Image.builder()
				.caption(caption)
				.postImageUrl(postImageUrl)
				.user(userEntity)
				.build();
				
	}
}
