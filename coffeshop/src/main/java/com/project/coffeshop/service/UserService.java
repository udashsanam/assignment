package com.project.coffeshop.service;

import com.project.coffeshop.entity.UserEntity;
import com.project.coffeshop.pojo.request.UserPojo;
import com.project.coffeshop.pojo.response.UserDto;

public interface UserService extends BaseService<UserEntity, Long> {

    UserDto signUp(UserPojo userPojo);
}
