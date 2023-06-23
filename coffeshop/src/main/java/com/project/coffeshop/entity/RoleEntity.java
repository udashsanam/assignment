package com.project.coffeshop.entity;

import com.project.coffeshop.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "role")
@Data
public class RoleEntity extends BaseEntity{

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private Role name;


}
