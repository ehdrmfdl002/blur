package com.blur.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequestDto {
	private String MemberId;
	private String password;
	
	public void encodePassword(String encodedPassword) {
        this.password = encodedPassword;
    }
}
