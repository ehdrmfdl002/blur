package com.blur.api.dto.response;

import com.blur.entity.EmailAuth;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class EmailAuthResponseDto {

    private Long tempNo;

    public EmailAuth toEntity(String authKey) {
        EmailAuth emailAuth = EmailAuth.builder()
                .authKey(authKey)
                .build();
        return emailAuth;
    }
}
