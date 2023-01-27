package com.blur.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user_profile")
public class MemberProfile {

    @Id
    @Column(name = "member_id")
    private String memberId;

    @Column(name = "birthyear")
    private Integer birthyear;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "image")
    private String image;

    @MapsId
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private Member member;

    public void setMember(Member member) {
        this.member = member;
        this.memberId = member.getMemberId();
    }

    public void updateProfile(Integer birthyear, String nickname, String image) {
        this.birthyear = birthyear;
        this.nickname = nickname;
        this.image = image;
    }

}
