package com.project.coffeshop.repo;

import com.project.coffeshop.entity.UserTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTokenRepository extends JpaRepository<UserTokenEntity, Long> {
}
