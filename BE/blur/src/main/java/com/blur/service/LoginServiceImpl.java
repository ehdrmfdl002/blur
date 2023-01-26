package com.blur.service;

import static com.blur.security.JwtConstants.AT_EXP_TIME;
import static com.blur.security.JwtConstants.AT_HEADER;
import static com.blur.security.JwtConstants.JWT_SECRET;
import static com.blur.security.JwtConstants.RT_EXP_TIME;
import static com.blur.security.JwtConstants.RT_HEADER;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.management.relation.Role;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.blur.entity.Token;
import com.blur.entity.User;
import com.blur.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class LoginServiceImpl implements LoginService, UserDetailsService {

	private final UserRepository userRepository;
	private final TokenService tokenService;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		User user = userRepository.findByUserId(userId)
				.orElseThrow(() -> new UsernameNotFoundException("아이디가 존재하지 않습니다"));

//        List<SimpleGrantedAuthority> authorities = account.getRoles()
//                .stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList();

		return new UserDetailsImpl(user);
	}

	// =============== TOKEN ============ //

	@Override
	public void updateRefreshToken(String userId, String refreshToken) throws Exception {
		User user = userRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
		Token token = new Token(userId, refreshToken);
		tokenService.saveRefreshToken(token);
	}

	@Override
	public Map<String, String> refresh(String refreshToken) {
		// === Refresh Token 유효성 검사 === //
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(JWT_SECRET)).build();
		DecodedJWT decodedJWT = verifier.verify(refreshToken);

		// === Access Token 재발급 === //
		long now = System.currentTimeMillis();
		String userId = decodedJWT.getSubject();
		User user = userRepository.findByUserId(userId)
				.orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
		String token = tokenService.getRefreshToken(userId);
		if (!token.equals(refreshToken)) {
			throw new JWTVerificationException("유효하지 않은 Refresh Token 입니다.");
		}
		String accessToken = JWT.create().withSubject(user.getUserId()).withExpiresAt(new Date(now + AT_EXP_TIME))
				.withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
				.sign(Algorithm.HMAC256(JWT_SECRET));
		Map<String, String> accessTokenResponseMap = new HashMap<>();

		// === 현재시간과 Refresh Token 만료날짜를 통해 남은 만료기간 계산 === //
		// === Refresh Token 만료시간 계산해 1개월 미만일 시 refresh token도 발급 === //
		long refreshExpireTime = decodedJWT.getClaim("exp").asLong() * 1000;
		long diffDays = (refreshExpireTime - now) / 1000 / (24 * 3600);
		long diffMin = (refreshExpireTime - now) / 1000 / 60;
		if (diffMin < 5) {
			String newRefreshToken = JWT.create().withSubject(user.getUserId())
					.withExpiresAt(new Date(now + RT_EXP_TIME)).sign(Algorithm.HMAC256(JWT_SECRET));
			accessTokenResponseMap.put(RT_HEADER, newRefreshToken);
			Token newToken = new Token(userId, newRefreshToken);
			tokenService.saveRefreshToken(newToken);
		}

		accessTokenResponseMap.put(AT_HEADER, accessToken);
		return accessTokenResponseMap;
	}
}
