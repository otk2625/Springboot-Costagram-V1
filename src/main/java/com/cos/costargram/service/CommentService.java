package com.cos.costargram.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cos.costargram.domain.comment.Comment;
import com.cos.costargram.domain.comment.CommentRepository;
import com.cos.costargram.domain.image.Image;
import com.cos.costargram.domain.user.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {
	private final CommentRepository commentRepository;
	
	@Transactional
	public Comment 댓글쓰기(User principal, String content, int imageId) {
		
		//id만 넣어줘도 알아서 찾아서 넣어준다
		Image image = Image.builder()
				.id(imageId)
				.build();
		
		//save할때 연관관계가 있으면 오브젝트로 만들어서 id값만 넣어주면 된다.
		Comment comment = Comment.builder()
				.content(content)
				.image(image)
				.user(principal)
				.build();
				
		return  commentRepository.save(comment);
	}
	
	@Transactional
	public void 댓글삭제(int id,int principalId) {
		
		Comment comment = commentRepository.findById(id).get();
		
		
		if(comment.getUser().getId() == principalId) {
			commentRepository.deleteById(id);
			
		}else {
			//여기서 스로우 익셉션을 날려서 컨트롤 어드바이스에서 처리해야함!
		}
		
		
	}
}
