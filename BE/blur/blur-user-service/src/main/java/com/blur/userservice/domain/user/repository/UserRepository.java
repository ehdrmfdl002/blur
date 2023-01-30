package com.blur.userservice.domain.user.repository;

import com.blur.userservice.domain.user.dto.UserDto;
import com.blur.userservice.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
	// username : pk
    Optional<User> findByEmail(String email);
    Optional<User> findByUserId(String userId);
    boolean existsByEmail(String email);
    boolean existsById(String id);
	void save(UserDto userDto);
}
