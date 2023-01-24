package com.blur.api.dto;

import com.blur.entity.Token;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class TokenDto {
	private Long userNo;
	private String refreshToken;
	private String userId;
	
	public Token toEntity() {
		Token token = Token.builder()
				.userNo(userNo)
				.refreshToken(refreshToken)
				.build();
		return token;
	}

	public TokenDto(Long userNo, String refreshToken) {
		super();
		this.userNo = userNo;
		this.refreshToken = refreshToken;
	}
}
