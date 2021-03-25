package com.cos.costargram.web.dto.user;

import com.cos.costargram.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserProfileRespDto {
	private boolean followState;
	private int followCount;
	private int imageCount;
	private User user;
}
