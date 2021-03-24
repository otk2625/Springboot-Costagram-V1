package com.cos.costargram.domain.folloew;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.costargram.domain.comment.Comment;
import com.cos.costargram.domain.image.Image;
import com.cos.costargram.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Follow {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	
	@JoinColumn(name = "fromUserId")
	@ManyToOne
	private User fromUser; // ~~로 부터 팔로우 하는사람
	
	@JoinColumn(name = "toUserId")
	@ManyToOne
	private User toUser; // ~~를 팔로우 당하는 사람
	
	
	@CreationTimestamp
	private Timestamp createDate;
}
