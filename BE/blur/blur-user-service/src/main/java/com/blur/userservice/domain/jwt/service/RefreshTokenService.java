package com.blur.userservice.domain.jwt.service;

import com.blur.userservice.domain.user.dto.JwtTokenDto;

public interface RefreshTokenService {
    void updateRefreshToken(Integer userNo, String uuid);
    JwtTokenDto refreshJwtToken(String accessToken, String refreshToken);
    void logoutToken(String accessToken);
}
