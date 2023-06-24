package com.project.coffeshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "coffee")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoffeeEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @OneToMany(mappedBy = "coffee")
    private List<OrderDetailEntity> orderDetailEntities;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cafe_id")
    private CafeEntity cafe;



}
