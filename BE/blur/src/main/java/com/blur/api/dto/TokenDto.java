package com.blur.api.dto;

import com.blur.entity.Token;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class TokenDto {
//	private Long userNo;
	private String userId;
	private String refreshToken;
	
//	public Token toEntity() {
//		Token token = Token.builder()
//				.userId(userId)
//				.refreshToken(refreshToken)
//				.build();
//		return token;
//	}

}
