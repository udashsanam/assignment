package com.project.coffeshop.service.impl;

import com.project.coffeshop.entity.UserRoleEntity;
import com.project.coffeshop.repo.UserRoleRepository;
import com.project.coffeshop.service.BaseService;
import com.project.coffeshop.service.UserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleEntity, Long> implements UserRoleService {

    private final UserRoleRepository userRoleRepository;


    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        super(userRoleRepository);
        this.userRoleRepository = userRoleRepository;
    }
}
