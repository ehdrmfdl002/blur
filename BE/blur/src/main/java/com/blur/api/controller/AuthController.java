package com.blur.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blur.api.dto.TokenDto;
import com.blur.api.dto.request.LoginRequestDto;
import com.blur.api.dto.response.ErrorResponse;
import com.blur.entity.User;
import com.blur.repository.UserRepository;
import com.blur.service.JwtServiceImpl;
import com.blur.service.TokenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
	private final Logger logger = LoggerFactory.getLogger(AuthController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	private final JwtServiceImpl jwtservice;
	private final AuthenticationManager authenticationManager;
	private final UserRepository userRepository;
	private final TokenService tokenService;
	private final MessageSource messageSource;
	private final PasswordEncoder passwordEncoder;

//	@PostMapping("/socialLogin")
//	public ApiResponse socialLogin(@RequestBody LoginRequestDto loginRequestDto) {
//		
//		Authentication authentication = authenticationManager.authenticate(
//			new UsernamePasswordAuthenticationToken(loginRequestDto.getUserId(), loginRequestDto.getPassword())
//		);
//				
//		String userId = loginRequestDto.getUserId();
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		
//		Date now = new Date();
//		
//		System.out.println(loginRequestDto.toString());
//		
//		try {
//			LoginRequestDto loginUser = memberservice.login(loginRequestDto);
//			if (loginUser != null) {
//				String accessToken = jwtservice.createAccessToken("userid", loginRequestDto.getUserId());// key, data
//				String refreshToken = jwtservice.createRefreshToken("userid", loginRequestDto.getUserId());// key, data
//				memberservice.saveRefreshToken(loginRequestDto.getUserId(), refreshToken);
//				logger.debug("로그인 accessToken 정보 : {}", accessToken);
//				logger.debug("로그인 refreshToken 정보 : {}", refreshToken);
//				resultMap.put("access-token", accessToken);
//				resultMap.put("refresh-token", refreshToken);
//				resultMap.put("message", SUCCESS);
//				status = HttpStatus.ACCEPTED;
//			} else {
//				resultMap.put("message", FAIL);
//				status = HttpStatus.ACCEPTED;
//			}
//		} catch (Exception e) {
//			logger.error("로그인 실패 : {}", e);
//			resultMap.put("message", e.getMessage());
//			status = HttpStatus.INTERNAL_SERVER_ERROR;
//		}
//		return ApiResponse.success("token", accessToken.getToken());
//	}
//	
	/**
	 * 로그인 JWT 발급
	 * 
	 * @param userInfo {userId, password}
	 * @return
	 */
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		User user = userRepository.findByUserId(loginRequestDto.getUserId());
		if (user == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(
					messageSource.getMessage("error.none.user", null, LocaleContextHolder.getLocale())));
		}

		if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(
					messageSource.getMessage("error.wrong.password", null, LocaleContextHolder.getLocale())));
		}
		
		String accessToken = jwtservice.createAccessToken("userId", loginRequestDto.getUserId());
		String refreshToken = jwtservice.createRefreshToken("userId", loginRequestDto.getUserId());
		logger.debug("로그인 accessToken 정보 : {}", accessToken);
		logger.debug("로그인 refreshToken 정보 : {}", refreshToken);
		resultMap.put("access-token", accessToken);
		resultMap.put("refresh-token", refreshToken);
		resultMap.put("message", SUCCESS);
		status = HttpStatus.ACCEPTED;
		
		return new ResponseEntity<>(resultMap, status);
	}

	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody TokenDto tokenDto) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		logger.debug("token : {}, memberDto : {}", tokenDto.getRefreshToken(), tokenDto.getUserId());
		if (jwtservice.checkToken(tokenDto.getRefreshToken())) {
			if (tokenDto.getRefreshToken().equals(tokenService.getRefreshToken(tokenDto.getUserId()))) {
				String accessToken = jwtservice.createAccessToken("userId", tokenDto.getUserId());
				logger.debug("token : {}", accessToken);
				logger.debug("정상적으로 액세스토큰 재발급");
				resultMap.put("access-token", accessToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}
		} else {
			logger.debug("리프레쉬 토큰 사용불가");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
