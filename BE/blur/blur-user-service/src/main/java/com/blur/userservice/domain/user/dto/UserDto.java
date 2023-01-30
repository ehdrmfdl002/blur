package com.blur.userservice.domain.user.dto;

import com.blur.userservice.domain.user.entity.AuthType;
import com.blur.userservice.domain.user.entity.User;

import lombok.Getter;

@Getter
public class UserDto {
    private int userNo;
    private String userId;
    private String email;
    private String password;
//    private String gender;
    private AuthType authType;
    
	public UserDto(User user) {
		this.userNo = user.getUserNo();
		this.userId = user.getUserId();
		this.email = user.getEmail();
		this.password = user.getPassword();
	}

	public UserDto(int userNo, String userId, String email, String password, AuthType authType) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.authType = authType;
	}

//    // == 생성 메소드 == //
//    public UserDto(Customer customer) {
//        this.id = customer.getId();
//        this.email = customer.getEmail();
//        this.password = customer.getPassword();
//        this.name = customer.getName();
//        this.phoneNumber = customer.getPhoneNumber();
//    }

    
}
