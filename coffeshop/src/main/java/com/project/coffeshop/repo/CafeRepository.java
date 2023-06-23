package com.project.coffeshop.repo;

import com.project.coffeshop.entity.CafeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CafeRepository extends JpaRepository<CafeEntity, Long> {
}
