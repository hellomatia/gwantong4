package com.ssafy.trip.user.model.dao;

import java.util.List;

import com.ssafy.trip.user.model.dto.UserDto;

public interface UserDao {
    UserDto login(String userId, String userPwd);

    int insertUser(UserDto user);

    int updateUser(UserDto user);

    int deleteUser(String userId);

    UserDto findUser(String userId);

    List<UserDto> findAllUser();

    String findPw(String userId);
}
