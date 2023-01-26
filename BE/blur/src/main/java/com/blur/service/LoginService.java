package com.blur.service;

import java.util.Map;

import com.blur.api.dto.request.LoginRequestDto;

public interface LoginService {
    void updateRefreshToken(String username, String refreshToken) throws Exception;
    Map<String, String> refresh(String refreshToken);
}
