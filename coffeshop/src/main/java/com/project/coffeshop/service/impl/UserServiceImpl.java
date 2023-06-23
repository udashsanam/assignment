package com.project.coffeshop.service.impl;

import com.project.coffeshop.entity.CafeEntity;
import com.project.coffeshop.entity.UserEntity;
import com.project.coffeshop.pojo.request.UserPojo;
import com.project.coffeshop.pojo.response.UserDto;
import com.project.coffeshop.repo.CafeRepository;
import com.project.coffeshop.repo.UserRepository;
import com.project.coffeshop.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final CafeRepository cafeRepository;


    public UserServiceImpl(UserRepository userRepository, CafeRepository cafeRepository) {
        super(userRepository);
        this.userRepository = userRepository;
        this.cafeRepository = cafeRepository;
    }

    @Override
    public UserDto signUp(UserPojo userPojo) {
        CafeEntity cafe = cafeRepository.findById(userPojo.getCafeId()).orElseThrow(()-> new RuntimeException("Cafe not found"))
        UserEntity user = new UserEntity();
        user.setName(userPojo.getName());
        user.setEmail(userPojo.getEmail());
        user.setCafe(cafe);
    }
}
