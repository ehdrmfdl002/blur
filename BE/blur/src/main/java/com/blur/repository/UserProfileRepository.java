package com.blur.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blur.entity.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    public UserProfile findByUserId(String userId);
}
