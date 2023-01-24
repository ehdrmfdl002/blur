package com.blur.repository;

import com.blur.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long>{
	Token findByUserNo(Long userNo);
//	Token findByUserNo(Long userNo);
	void deleteByuserNo(Long userNo);
}
