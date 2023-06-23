package com.project.coffeshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "cafe")
@Data
public class CafeEntity extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;



}
