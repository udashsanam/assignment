package com.project.coffeshop.service.impl;

import com.project.coffeshop.entity.CafeEntity;
import com.project.coffeshop.entity.RoleEntity;
import com.project.coffeshop.entity.UserEntity;
import com.project.coffeshop.entity.UserRoleEntity;
import com.project.coffeshop.enums.Role;
import com.project.coffeshop.pojo.request.LoginPojo;
import com.project.coffeshop.pojo.request.UserPojo;
import com.project.coffeshop.pojo.response.TokenResponse;
import com.project.coffeshop.pojo.response.UserDto;
import com.project.coffeshop.repo.*;
import com.project.coffeshop.service.UserTokenService;
import com.project.coffeshop.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@WebMvcTest(UserServiceImpl.class)
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @MockBean
    private  UserRepository userRepository;

    @MockBean
    private  CafeRepository cafeRepository;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private BCryptPasswordEncoder passwordEncoder;

    @MockBean
    private  UserTokenService userTokenService;
    @MockBean
    private UserTokenRepository userTokenRepository;

    @MockBean
    private UserRefreshTokenRepo userRefreshTokenRepo;

    @MockBean
    private UserRoleRepository userRoleRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Customer Sign up test")
    void customerSignUpTest() {

        UserPojo userPojo = new UserPojo();
        userPojo.setRole(Role.CUSTOMER.toString());
        userPojo.setName("test");
        userPojo.setPassword("Admin@123");
        userPojo.setEmail("test@gmail.com");
        userPojo.setUsername("test");

        UserEntity user = new UserEntity();
        user.setUsername("test");
        user.setName("test");
        user.setPassword("encrypted");
        user.setEmail("test@gmail.com");

        Mockito.when(userRepository.save(user)).thenReturn(user);

        Mockito.when(userRepository.findByUsername(userPojo.getUsername())).thenReturn(null);


        Mockito.when(userRoleRepository.save(any())).thenReturn(null);

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName(Role.CUSTOMER);
        Mockito.when(roleRepository.findByName(Role.CUSTOMER)).thenReturn(roleEntity);

        Mockito.when(passwordEncoder.encode("Admin@123")).thenReturn("encrypted");

        UserDto userDto = userService.signUp(userPojo);

        assertEquals("test", userDto.getUsername());
        assertEquals("test@gmail.com", userDto.getEmail());

    }

    @Test
    @DisplayName("Cafe Owner Sign up test")
    void cafeOwnerSignUpTest() {

        UserPojo userPojo = new UserPojo();
        userPojo.setRole(Role.CAFE_OWNER.toString());
        userPojo.setName("test");
        userPojo.setPassword("Admin@123");
        userPojo.setEmail("test@gmail.com");
        userPojo.setUsername("test");
        userPojo.setCafeId(1l);

        UserEntity user = new UserEntity();
        CafeEntity cafe = new CafeEntity();
        cafe.setName("test");
        user.setUsername("test");
        user.setName("test");
        user.setPassword("encrypted");
        user.setEmail("test@gmail.com");
        user.setCafe(cafe);

        Mockito.when(userRepository.save(user)).thenReturn(user);

        Mockito.when(userRepository.findByUsername(userPojo.getUsername())).thenReturn(null);


        Mockito.when(cafeRepository.findById(1l)).thenReturn(Optional.of(cafe));


        Mockito.when(userRoleRepository.save(any())).thenReturn(null);

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName(Role.CAFE_OWNER);
        Mockito.when(roleRepository.findByName(Role.CAFE_OWNER)).thenReturn(roleEntity);

        Mockito.when(passwordEncoder.encode("Admin@123")).thenReturn("encrypted");

        UserDto userDto = userService.signUp(userPojo);

        assertEquals("test", userDto.getUsername());
        assertEquals("test@gmail.com", userDto.getEmail());

    }

    @Test
    @DisplayName("sign in test")
    void signIn() {
        LoginPojo loginPojo = new LoginPojo();
        loginPojo.setUsername("test");
        loginPojo.setPassword("Admin@123");

        UserEntity userEntity = new UserEntity();
        userEntity.setPassword("test");

        TokenResponse response = new TokenResponse();
        response.setAccessToken("access token");
        response.setRefreshToken("refresh token");

        Mockito.when(userRepository.findByUsername("test")).thenReturn(userEntity);
        Mockito.when(passwordEncoder.matches(any(CharSequence.class), anyString())).thenReturn(true);
        Mockito.when(userTokenService.getToken(userEntity)).thenReturn(response);

        TokenResponse response1 = userService.signIn(loginPojo);
        assertEquals("access token", response1.getAccessToken());
        assertEquals("refresh token", response1.getRefreshToken());
    }

    @Test
    void signOut() {
    }

    @Test
    void getToken() {
    }
}