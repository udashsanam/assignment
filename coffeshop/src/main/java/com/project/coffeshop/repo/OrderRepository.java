package com.project.coffeshop.repo;

import com.project.coffeshop.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findAllByUserId(Long id);

    List<OrderEntity> findAllByCafeId(Long cafeId);
}
