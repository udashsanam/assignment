package com.project.coffeshop.service;

import com.project.coffeshop.entity.UserEntity;
import com.project.coffeshop.entity.UserTokenEntity;
import com.project.coffeshop.pojo.response.TokenResponse;
import io.jsonwebtoken.Claims;

import java.util.UUID;

public interface UserTokenService extends BaseService<UserTokenEntity, UUID> {

    TokenResponse getToken(UserEntity user);

    Claims getClaims(String token);

    UserEntity getUser(String token);
}
