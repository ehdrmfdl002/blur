package com.blur.api.dto;

import com.blur.entity.Token;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class TokenDto {
//	private Long MemberNo;
	private String MemberId;
	private String refreshToken;
	
//	public Token toEntity() {
//		Token token = Token.builder()
//				.MemberId(MemberId)
//				.refreshToken(refreshToken)
//				.build();
//		return token;
//	}

}
