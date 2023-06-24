package com.project.coffeshop.service;

import com.project.coffeshop.entity.UserEntity;
import com.project.coffeshop.pojo.request.LoginPojo;
import com.project.coffeshop.pojo.request.UserPojo;
import com.project.coffeshop.pojo.response.TokenResponse;
import com.project.coffeshop.pojo.response.UserDto;

public interface UserService extends BaseService<UserEntity, Long> {

    UserDto signUp(UserPojo userPojo);

    TokenResponse signIn(LoginPojo loginPojo);

    boolean signOut(String username, String token);

    TokenResponse getToken(String refreshToken);
}
