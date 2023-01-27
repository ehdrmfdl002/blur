package com.blur.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blur.entity.MemberProfile;

public interface MemberProfileRepository extends JpaRepository<MemberProfile, Long> {
    public MemberProfile findByMemberId(String userId);
}
