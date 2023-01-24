package com.blur.api.dto.request;

import lombok.Data;

@Data
public class LoginRequestDto {
	private String userId;
	private String password;
}
