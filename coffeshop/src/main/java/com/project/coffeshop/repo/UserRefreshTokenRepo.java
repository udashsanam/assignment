package com.project.coffeshop.repo;

import com.project.coffeshop.entity.UserRefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRefreshTokenRepo extends JpaRepository<UserRefreshTokenEntity, UUID> {

    UserRefreshTokenEntity findByUserTokenId(UUID id);

}
