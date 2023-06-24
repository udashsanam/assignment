package com.project.coffeshop.repo;

import com.project.coffeshop.entity.UserEntity;
import com.project.coffeshop.entity.UserTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);


}
