package com.blur.repository;

import com.blur.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(String userId);
//    public User findByUserNo(Long userNo);
    boolean existsByUserId(String userId);
}
