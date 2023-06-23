package com.project.coffeshop.repo;

import com.project.coffeshop.entity.UserRefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRefreshTokenRepo extends JpaRepository<UserRefreshTokenEntity, Long> {
}
