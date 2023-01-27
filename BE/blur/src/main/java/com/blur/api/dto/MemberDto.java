package com.blur.api.dto;

import com.blur.entity.Member;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class MemberDto {

    private String MemberId;

    private String password;

    private String email;

    private Boolean gender;

    /* DTO -> Entity */
    public Member toEntity() {
    	Member member = Member.builder()
                .memberId(MemberId)
                .password(password)
                .email(email)
                .build();
        return member;
    }

    public MemberDto(Member member) {
        this.MemberId = member.getMemberId();
        this.password = member.getPassword();
        this.email = member.getEmail();
        this.gender = member.getGender();
    }

}
