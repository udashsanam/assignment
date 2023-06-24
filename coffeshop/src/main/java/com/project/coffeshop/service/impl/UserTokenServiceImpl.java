package com.project.coffeshop.service.impl;

import com.project.coffeshop.entity.UserEntity;
import com.project.coffeshop.entity.UserRefreshTokenEntity;
import com.project.coffeshop.entity.UserRoleEntity;
import com.project.coffeshop.entity.UserTokenEntity;
import com.project.coffeshop.pojo.response.TokenResponse;
import com.project.coffeshop.repo.UserRepository;
import com.project.coffeshop.repo.UserRoleRepository;
import com.project.coffeshop.repo.UserTokenRepository;
import com.project.coffeshop.service.UserTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static com.project.coffeshop.util.Constants.*;

@Service
@Transactional
public class UserTokenServiceImpl extends BaseServiceImpl<UserTokenEntity, UUID> implements UserTokenService {



    private final UserTokenRepository userTokenRepository;


    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;


    public UserTokenServiceImpl(UserTokenRepository userTokenRepository) {
        super(userTokenRepository);
        this.userTokenRepository = userTokenRepository;
    }

    @Override
    public UserEntity getUser(String token) {
        String onlineUser = this.getClaims(token).getSubject();
        UserEntity user = userRepository.findByUsername(onlineUser);
        if(user == null) throw new RuntimeException("user not found");
        return user;
    }

    @Override
    public TokenResponse getToken(UserEntity user) {
        String accessToken = getAccessToken(user);
        String refreshToken = UUID.randomUUID().toString();
        UserTokenEntity userToken = UserTokenEntity.builder()
                .accessToken(accessToken)
                .expiryTime(new Timestamp(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE_TIME))
                .user(user)
                .build();

        try {
            userToken = save(userToken);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new RuntimeException("Error saving the  access token");
        }

        UserRefreshTokenEntity userRefreshTokenEntity = UserRefreshTokenEntity.builder()
                .userToken(userToken)
                .refreshToken(refreshToken)
                .expiryTime(new Timestamp(System.currentTimeMillis() + SESSION_EXPIRY_TIME))
                .build();


        return new TokenResponse(accessToken, refreshToken);
    }

    private String getAccessToken(UserEntity user) {

        List<UserRoleEntity> userRoleEntities = userRoleRepository.findAllByUserEntityId(user.getId());
        String roles = userRoleEntities.stream().map(userRoleEntity -> userRoleEntity.getRole().getName().toString()).collect(Collectors.joining(","));

        Map<String, Object> map = new HashMap<>();
        map.put(USER_ID_CLAIM, user.getId());
        map.put(USER_ROLES_CLAIM, roles);

        return Jwts.builder()
                .setClaims(map)
                .setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis()
                        + ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }


    @Override
    public Claims getClaims(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }



}
