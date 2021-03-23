package com.cos.costargram.domain.user;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	
	@Column(unique = true, length = 30) //DB 제약조건
	private String username;
	
	@JsonIgnore //파싱 불가능하게
	private String password;
	
	private String name;
	private String webSite; //자기 홈페이지
	private String bio; //자기소개
	private String email;
	private String phone;
	private String gender;
	
	private String profileImage;
	private String provider; //제공자 Google, Facebook, Naver
	
	@CreationTimestamp
	private Timestamp createDate;
}
