package com.cos.costargram.web.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.costargram.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class UserJoinReqDto {


	private String username;
	private String password;
	private String name;
	private String email;


	public User toEntity() {
		User user = User.builder()
				.username(username)
				.password(password)
				.name(name)
				.email(email)
				.build();
		
		return user;
	}

}
