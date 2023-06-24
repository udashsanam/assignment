package com.project.coffeshop.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class UserEntity extends BaseEntity{

    private String name;

    private String username;

    private String password;

    private String email;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "cafe_id", nullable = true)
    private CafeEntity cafe;
}
