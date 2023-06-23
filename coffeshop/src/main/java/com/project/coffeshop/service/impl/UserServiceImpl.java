package com.project.coffeshop.service.impl;

import com.project.coffeshop.entity.CafeEntity;
import com.project.coffeshop.entity.RoleEntity;
import com.project.coffeshop.entity.UserEntity;
import com.project.coffeshop.enums.Role;
import com.project.coffeshop.exception.UnAuthorizeException;
import com.project.coffeshop.pojo.request.LoginPojo;
import com.project.coffeshop.pojo.request.UserPojo;
import com.project.coffeshop.pojo.response.TokenResponse;
import com.project.coffeshop.pojo.response.UserDto;
import com.project.coffeshop.repo.CafeRepository;
import com.project.coffeshop.repo.RoleRepository;
import com.project.coffeshop.repo.UserRepository;
import com.project.coffeshop.service.UserService;
import com.project.coffeshop.service.UserTokenService;
import com.project.coffeshop.util.Constants;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<UserEntity, Long> implements UserService {

    private final UserRepository userRepository;

    private final CafeRepository cafeRepository;

    private final RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private final UserTokenService userTokenService;


    public UserServiceImpl(UserRepository userRepository, CafeRepository cafeRepository, RoleRepository roleRepository, UserTokenService userTokenService) {
        super(userRepository);
        this.userRepository = userRepository;
        this.cafeRepository = cafeRepository;
        this.roleRepository = roleRepository;
        this.userTokenService = userTokenService;
    }

    @Override
    public UserDto signUp(UserPojo userPojo) {
        RoleEntity role = roleRepository.findById(userPojo.getRoleId()).orElseThrow(()-> new RuntimeException("Role not found"));
        UserEntity user = new UserEntity();
        if(Role.CAFE_OWNER.equals(role.getName())) {
            CafeEntity cafe = cafeRepository.findById(userPojo.getCafeId()).orElseThrow(()-> new RuntimeException("Cafe not found"));
            user.setCafe(cafe);
        }
        user.setName(userPojo.getName());
        user.setEmail(userPojo.getEmail());
        user.setUsername(userPojo.getUsername());
        user.setPassword(passwordEncoder.encode(userPojo.getPassword()));

        try {
            user = save(user);
        } catch (Exception ex){
            throw new RuntimeException("Error saving the user");
        }

        return new UserDto(user.getUsername(), user.getEmail(), user.getCafe().getName());
    }

    @Override
    public TokenResponse signIn(LoginPojo loginPojo) {
        UserEntity userEntity = userRepository.findByUsername(loginPojo.getUsername());
        if(userEntity == null) throw new RuntimeException("User not found");
        if(!passwordEncoder.matches(loginPojo.getPassword(), userEntity.getPassword())) throw new RuntimeException("Password not match");
        return userTokenService.getToken(userEntity);
    }

    @Override
    public boolean signOut(String username, String token) {

        UserEntity user = userRepository.findByUsername(username);
        if(user == null) throw new RuntimeException("User not found");
        Claims  claims = userTokenService.getClaims(token);
        if(!username.equals(claims.getSubject())) throw new UnAuthorizeException("You are unauthorized to logout this user");
        // todo expired all the sessioin
        return true;
    }


}
