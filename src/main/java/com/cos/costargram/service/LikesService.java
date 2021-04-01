package com.cos.costargram.service;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.costargram.domain.image.Image;
import com.cos.costargram.domain.image.ImageRepository;
import com.cos.costargram.domain.likes.Likes;
import com.cos.costargram.domain.likes.LikesRepository;
import com.cos.costargram.domain.user.User;
import com.cos.costargram.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LikesService {
	private final LikesRepository likesRepository;
	private final ImageRepository imageRepository;
	private final UserRepository userRepository;

	@Transactional
	public void 좋아요(int imageId, int id) {
		
		User user = userRepository.findById(id).get();
		
		Image image = imageRepository.findById(imageId).get();
		
		Likes likes = Likes.builder()
				.user(user)
				.image(image)
				.build();
		
		likesRepository.save(likes);
		
		
		
		//likesRepository.mLike(imageId, id);
		
	}

	@Transactional
	public void 싫어요(int imageId, int id) {

		
		likesRepository.mdelete(imageId, id);
		
	}
	
	
}
