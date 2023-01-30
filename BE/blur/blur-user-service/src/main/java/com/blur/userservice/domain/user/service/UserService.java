package com.blur.userservice.domain.user.service;

import java.util.List;

import com.blur.userservice.domain.user.dto.UserDto;
import com.blur.userservice.domain.user.entity.User;

public interface UserService {
    UserDto findUserByUserId(Integer userId);
    List<UserDto> findUserDtoByUserIds(List<Integer> userIds);
//    UserDto findOwnerById(Long userId);
    void saveUser(UserDto userDto);
//    User register(UserDto dto);
}
