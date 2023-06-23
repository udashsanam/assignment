package com.project.coffeshop.service.impl;

import com.project.coffeshop.entity.UserRefreshTokenEntity;
import com.project.coffeshop.repo.UserRefreshTokenRepo;
import com.project.coffeshop.service.UserRefreshTokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserRefreshTokenServiceImpl extends BaseServiceImpl<UserRefreshTokenEntity,Long> implements UserRefreshTokenService {

    private final UserRefreshTokenRepo userRefreshTokenRepo;

    public UserRefreshTokenServiceImpl(UserRefreshTokenRepo userRefreshTokenRepo) {
        super(userRefreshTokenRepo);
        this.userRefreshTokenRepo = userRefreshTokenRepo;
    }
}
