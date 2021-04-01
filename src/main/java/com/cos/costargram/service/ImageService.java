package com.cos.costargram.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.costargram.config.auth.PrincipalDetails;
import com.cos.costargram.domain.image.Image;
import com.cos.costargram.domain.image.ImageRepository;
import com.cos.costargram.domain.tag.Tag;
import com.cos.costargram.domain.tag.TagRepository;
import com.cos.costargram.utils.TagUtils;
import com.cos.costargram.web.dto.image.ImageReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {
	
	private final ImageRepository imageRepository;
	private final TagRepository tagRepository;
	@Value("${file.path}") // application.yml 파일에 접근가능
	private String uploadFolder;
	
	public List<Image> 피드이미지(int principalId){
		
		//1. principalId로 내가 팔로우 하고있는 사용자를 찾아야 됨.(한개이거나 컬렉션이거나)
		//select * from image where userId in (select toUserId from follow where fromUserId = principalId(로그인 아이디));
		
		List<Image> images = imageRepository.mFeeds(principalId);
		
		//좋아요 로직
		images.forEach((image) -> {
			
			int likeCount = image.getLikes().size();
			image.setLikeCount(likeCount);
			
			image.getLikes().forEach((like)->{
				if(like.getUser().getId() == principalId) {
					image.setLikeState(true);
					
				}
			});
		});
		
		return images;
		
	}

	@Transactional
	public void 사진업로드(ImageReqDto imageReqDto, PrincipalDetails principalDetails) {
		
		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid + "_" +imageReqDto.getFile().getOriginalFilename();
		//System.out.println("파일명 : " + imageFileName);
		
		Path inageFilePath = Paths.get(uploadFolder + imageFileName);
		//System.out.println("파일패스 : " + inageFilePath);
		
		try {
			Files.write(inageFilePath, imageReqDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//참고 : image 엔티티에 Tag는 주인이 아니다. Image 엔티티로 통해서 Tag를 save할 수 없다.
		
		//1. Image저장
		Image image = imageReqDto.toEntity(imageFileName, principalDetails.getUser());
		Image imageEntity = imageRepository.save(image);
		
		//2. Tag저장
		List<Tag> tags = TagUtils.parsingToTagObject(imageReqDto.getTags(), imageEntity);
		tagRepository.saveAll(tags);
	}

	@Transactional(readOnly = true)
	public List<Image> 인기사진(int principalId){
		return imageRepository.mExplore(principalId);
	}
}
