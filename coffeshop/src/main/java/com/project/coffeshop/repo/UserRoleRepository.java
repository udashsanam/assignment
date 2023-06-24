package com.project.coffeshop.repo;

import com.project.coffeshop.entity.UserRoleEntity;
import com.project.coffeshop.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    List<UserRoleEntity> findAllByUserEntityId(Long id);

    @Query("select ur.role.name from UserRoleEntity ur where ur.userEntity.id = ?1")
    List<String> findAllRolesByUserId(Long userId);

}
