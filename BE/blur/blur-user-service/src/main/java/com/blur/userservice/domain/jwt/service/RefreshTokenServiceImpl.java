package com.blur.userservice.domain.jwt.service;

import com.blur.userservice.domain.jwt.exception.AccessTokenNotValidException;
import com.blur.userservice.domain.jwt.exception.RefreshTokenNotValidException;
import com.blur.userservice.domain.jwt.redis.RefreshToken;
import com.blur.userservice.domain.jwt.repository.RefreshTokenRedisRepository;
import com.blur.userservice.global.utils.JwtTokenProvider;
import com.blur.userservice.domain.user.dto.JwtTokenDto;
import com.blur.userservice.domain.user.entity.User;
import com.blur.userservice.domain.user.exception.NotExistUserException;
import com.blur.userservice.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final RefreshTokenRedisRepository refreshTokenRedisRepository;

    @Transactional
    @Override
    public void updateRefreshToken(Integer userNo, String uuid) {
        User user = userRepository.findById(userNo)
                .orElseThrow(() -> new NotExistUserException("사용자 고유번호 : " + userNo + "는 없는 사용자입니다."));

        refreshTokenRedisRepository.save(RefreshToken.of(user.getUserNo().toString(), uuid));
    }

    @Transactional
    @Override
    public JwtTokenDto refreshJwtToken(String accessToken, String refreshToken) {
        String userNo = jwtTokenProvider.getUserId(accessToken);

        RefreshToken findRefreshToken = refreshTokenRedisRepository.findById(userNo)
                .orElseThrow(()
                        -> new RefreshTokenNotValidException("사용자 고유번호 : " + userNo + "는 등록된 리프레쉬 토큰이 없습니다.")
                );

        // refresh token 검증
        String findRefreshTokenId = findRefreshToken.getRefreshTokenId();
        if (!jwtTokenProvider.validateJwtToken(refreshToken)) {
            refreshTokenRedisRepository.delete(findRefreshToken);
            throw new RefreshTokenNotValidException("Not validate jwt token = " + refreshToken);
        }

        if (!jwtTokenProvider.equalRefreshTokenId(findRefreshTokenId, refreshToken)) {
            throw new RefreshTokenNotValidException("redis 의 값과 일치하지 않습니다. = " + refreshToken);
        }

        User findUser = userRepository.findById(Integer.valueOf(userNo))
                .orElseThrow(() -> new NotExistUserException("유저 고유 번호 : " + userNo + "는 없는 유저입니다."));

        // access token 생성
        Authentication authentication = getAuthentication(findUser.getEmail());
        List<String> roles = authentication.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        String newAccessToken = jwtTokenProvider.createJwtAccessToken(userNo, "/reissu", roles);
        Date expiredTime = jwtTokenProvider.getExpiredTime(newAccessToken);

        return JwtTokenDto.builder()
                .accessToken(newAccessToken)
                .accessTokenExpiredDate(expiredTime)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public void logoutToken(String accessToken) {
        if (!jwtTokenProvider.validateJwtToken(accessToken)) {
            // 예외 발생
            throw new AccessTokenNotValidException("access token is not valid");
        }

        RefreshToken refreshToken = refreshTokenRedisRepository.findById(jwtTokenProvider.getUserId(accessToken))
                .orElseThrow(() -> new RefreshTokenNotValidException("refresh Token is not exist"));

        refreshTokenRedisRepository.delete(refreshToken);
    }

    public Authentication getAuthentication(String email) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }
}
