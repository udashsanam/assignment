package com.project.coffeshop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class UserEntity extends BaseEntity{

    private String name;

    private String username;

    private String password;

    private String email;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "cafe_id", nullable = true)
    private CafeEntity cafe;
}
