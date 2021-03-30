package com.cos.costargram.web.dto.follow;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FollowRespDto {
	private int userId;
	private String username;
	private String prodileImageUrl;
	private BigInteger followState;
	private BigInteger equalState;
}	
