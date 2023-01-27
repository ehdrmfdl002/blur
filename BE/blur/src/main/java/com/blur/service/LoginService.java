package com.blur.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blur.api.dto.response.LoginResponseDto;
import com.blur.auth.MyUserDetailsService;
import com.blur.auth.jwt.JwtTokenProvider;
import com.blur.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenProvider jwtTokenProvider;
	private final MyUserDetailsService myUserDetailsService;

	@Transactional()
	public LoginResponseDto Login(String userId, String password) {
		UserDetails userDetails = myUserDetailsService.loadUserByUsername(userId);

		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException(userDetails.getUsername() + "Invalid password");
		}

		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
				userDetails.getPassword(), userDetails.getAuthorities());

		log.info("signIn service | authentication.getName : {}, authentication.getCredentials() : {}",
				authentication.getName(), authentication.getCredentials());

		return new LoginResponseDto("Bearer-" + jwtTokenProvider.createAccessToken(authentication),
				"Bearer-" + jwtTokenProvider.issueRefreshToken(authentication));
	}
}
