package com.project.coffeshop.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "order_detail")
@Data
public class OrderDetailEntity extends BaseEntity{

    @Column(name = "quntity")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;


    @ManyToOne
    @JoinColumn(name = "coffee_id", nullable = false)
    private CoffeeEntity coffee;


    @Column(name = "descriptin")
    private String description;
}
