package com.ssafy.trip.user.model.service;

import java.util.List;

import com.ssafy.trip.user.model.dao.UserDao;
import com.ssafy.trip.user.model.dao.UserDaoImpl;
import com.ssafy.trip.user.model.dto.UserDto;

public class UserServiceImpl implements UserService {

    private static final UserService instance = new UserServiceImpl();
    private static final UserDao userDao = UserDaoImpl.getInstance();

    private UserServiceImpl() {
    }

    public static UserService getInstance() {
        return instance;
    }

    @Override
    public UserDto login(String userId, String userPwd) {
        return userDao.login(userId, userPwd);
    }

    @Override
    public int insertUser(UserDto user) {
        return userDao.insertUser(user);
    }

    @Override
    public int updateUser(UserDto user) {
        return userDao.updateUser(user);
    }

    @Override
    public int deleteUser(String userId) {
        return userDao.deleteUser(userId);
    }

    @Override
    public UserDto findUser(String userId) {
        return userDao.findUser(userId);
    }

    @Override
    public List<UserDto> findAllUser() {
        return userDao.findAllUser();
    }

    @Override
    public String findPw(String userId) {
        return userDao.findPw(userId);
    }
}
