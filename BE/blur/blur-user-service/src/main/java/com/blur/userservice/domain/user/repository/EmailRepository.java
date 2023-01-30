package com.blur.userservice.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blur.userservice.domain.user.entity.EmailAuth;

public interface EmailRepository extends JpaRepository<EmailAuth, Long> {

    public EmailAuth findByTempNo(String tempNo);
}
