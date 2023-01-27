package com.blur.service;

import org.springframework.stereotype.Service;

import com.blur.api.dto.TokenDto;
import com.blur.entity.Token;
import com.blur.repository.TokenRepository;
import com.blur.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService{
	private final TokenRepository tokenRepository;
	
	public void saveRefreshToken(Token token) throws Exception {
		tokenRepository.save(token);
	}

//	public String getRefreshToken(String memberId) throws Exception {
//		return tokenRepository.findByMemberId(memberId).getRefreshToken();
//	}

	public void deleRefreshToken(String memberId) throws Exception {
		tokenRepository.deleteByMemberId(memberId);
	}
	}
