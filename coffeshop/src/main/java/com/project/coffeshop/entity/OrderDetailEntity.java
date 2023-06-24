package com.project.coffeshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_detail")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailEntity extends BaseEntity{

    @Column(name = "quntity")
    private Integer quantity;



    @Column(name = "descriptin")
    private String description;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;


    @ManyToOne
    @JoinColumn(name = "coffee_id", nullable = false)
    private CoffeeEntity coffee;

}
