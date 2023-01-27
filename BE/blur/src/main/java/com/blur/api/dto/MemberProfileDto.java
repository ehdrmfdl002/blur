package com.blur.api.dto;

import com.blur.entity.Member;
import com.blur.entity.MemberProfile;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class MemberProfileDto {

    private String MemberId;

    private Integer birthyear;

    private String nickname;

    private String image;

    private Boolean gender;

    public MemberProfile toEntity(Member member) {
    	MemberProfile memberProfile = MemberProfile.builder()
                .memberId(member.getMemberId())
                .birthyear(birthyear)
                .nickname(nickname)
                .image(image)
                .build();
        return memberProfile;
    }

    public MemberProfileDto(Member member, MemberProfile MemberProfile) {
        this.MemberId = MemberProfile.getMemberId();
        this.birthyear = MemberProfile.getBirthyear();
        this.nickname = MemberProfile.getNickname();
        this.image = MemberProfile.getImage();
        this.gender = member.getGender();
    }
}
