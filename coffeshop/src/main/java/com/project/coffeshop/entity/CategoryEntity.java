package com.project.coffeshop.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "category")
@Data
public class CategoryEntity extends BaseEntity{

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
    private List<CoffeeEntity>  coffeeEntities;
}
