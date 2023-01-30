package com.blur.userservice.domain.user.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.blur.userservice.global.entity.BaseEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "user_no")
    private Integer userNo;

    private String userId; 
    
    private String email;

    private String password;
    
    private String gender;
    
//    private String gender;

//    private String name;
//
//    private String phoneNumber;

//    @Column(insertable = false,updatable = false)
//    protected String dtype;
    
    @Enumerated(EnumType.STRING)
    private AuthType oauthType;

    public User(String loginId, String email, String password, AuthType oauthType) {
        this.email = email;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.oauthType = oauthType;
    }

}
