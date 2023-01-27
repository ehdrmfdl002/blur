package com.blur.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blur.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserId(String userId);
//    public User findByUserNo(Long UserNo);
//    boolean existsByUserId(String UserId);
}
