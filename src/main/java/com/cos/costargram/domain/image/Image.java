package com.cos.costargram.domain.image;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.costargram.domain.comment.Comment;
import com.cos.costargram.domain.likes.Likes;
import com.cos.costargram.domain.tag.Tag;
import com.cos.costargram.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	
	private String caption; // 오늘 나 너무 피곤했어!!
	private String postImageUrl;
	
	@JsonIgnoreProperties({"images"})
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	
	@JsonIgnoreProperties({"image"})
	@OneToMany(mappedBy = "image")
	private List<Tag> tags;
	
	@JsonIgnoreProperties({"image"})
	@OneToMany(mappedBy = "image")
	private List<Likes> likes; //A이미지에 홍길동, 장보고, 임꺽정 좋아요. (고소영)
	
	//follow 정보
	
	//comment(댓긓)
	@OrderBy("id DESC")
	@JsonIgnoreProperties({"image"})
	@OneToMany(mappedBy = "image")
	private List<Comment> comments;
	
	@CreationTimestamp
	private Timestamp createDate;
	
	@Transient // 칼럼이 만들어지지 않는다.
	private int likeCount;
	
	@Transient
	private boolean likeState;
}
