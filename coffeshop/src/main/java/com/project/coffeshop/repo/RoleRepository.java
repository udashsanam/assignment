package com.project.coffeshop.repo;

import com.project.coffeshop.entity.RoleEntity;
import com.project.coffeshop.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByName(Role role);

}
