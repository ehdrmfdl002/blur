package com.blur.userservice.domain.user.dto;

import com.blur.userservice.domain.user.entity.EmailAuth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class EmailAuthDto {

    private Long tempNo;

    private String authKey;


    public EmailAuth toEntity(String authKey) {
        this.authKey = authKey;
        EmailAuth emailAuth = EmailAuth.builder()
                .authKey(authKey)
                .build();
        return emailAuth;
    }
}
