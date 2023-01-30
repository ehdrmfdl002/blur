package com.blur.userservice.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blur.userservice.domain.user.entity.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
    public UserProfile findByUserId(String userId);
}
