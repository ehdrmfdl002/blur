package com.blur.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "member")
public class Member {

//    @JsonIgnore
//    @Id
//    @Column(name = "user_no")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Long userNo;

	@Id
    @Column(name = "member_id", nullable = false, length = 255, unique = true)
    String memberId;

    @Column(name = "email", length = 30)
    String email;

    @JsonIgnore
    @Column(name = "password", length = 255)
    String password;

    @Column(name = "gender")
    Boolean gender;
    
    @Column(name = "roles", length = 255)
    String roles;
    
    @OneToOne(mappedBy = "member")
    private MemberProfile memberProfile;

    public void updateGender(Boolean gender) {
        this.gender = gender;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
    
    public List<String> getRoleList() {
        if (roles.length() > 0) {
            return Arrays.asList(roles.split(","));
        }
        return new ArrayList<>();
    }
    
    @Builder
    public Member(String memberId, String password) {
        this.memberId = memberId;
        this.password = password;
        this.roles = "ROLE_USER";
    }
    
    public static Member testCreate(String memberId, String password) {
        return Member.builder()
                .memberId(memberId)
                .password(password)
                .build();
    }
}
