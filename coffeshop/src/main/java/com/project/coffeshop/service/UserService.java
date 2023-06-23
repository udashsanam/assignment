package com.project.coffeshop.service;

import com.project.coffeshop.pojo.request.UserPojo;
import com.project.coffeshop.pojo.response.UserDto;

public interface UserService {

    UserDto signUp(UserPojo userPojo);
}
