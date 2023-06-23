package com.project.coffeshop.service.impl;

import com.project.coffeshop.repo.RoleRepository;
import com.project.coffeshop.service.RoleServcie;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl extends BaseServiceImpl implements RoleServcie {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        super(roleRepository);
        this.roleRepository = roleRepository;
    }
}
