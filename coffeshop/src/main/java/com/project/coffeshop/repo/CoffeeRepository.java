package com.project.coffeshop.repo;

import com.project.coffeshop.entity.CoffeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoffeeRepository extends JpaRepository<CoffeeEntity, Long> {

    List<CoffeeEntity> findAllByCafeId(Long id);
}
