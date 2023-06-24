package com.project.coffeshop.repo;

import com.project.coffeshop.entity.UserEntity;
import com.project.coffeshop.entity.UserTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserTokenRepository extends JpaRepository<UserTokenEntity, UUID> {

    UserTokenEntity findByAccessToken(String token);


}
