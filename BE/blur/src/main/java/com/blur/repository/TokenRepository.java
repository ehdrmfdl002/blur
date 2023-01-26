package com.blur.repository;

import com.blur.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, String>{
	Token findByUserId(String userId);
	void deleteByuserId(String userId);
}
