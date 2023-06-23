package com.project.coffeshop.entity;

import com.project.coffeshop.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "order_tbl")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity extends BaseEntity{

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;


    @Column(name = "order_total")
    private Double orderTotal;

    @OneToMany(mappedBy = "order",fetch = FetchType.EAGER)
    private List<OrderDetailEntity> orderDetailEntities;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cafe_id")
    private CafeEntity cafe;



//    public void setStatus(String status) {
//        this.status = OrderStatus.valueOf(status);
//    }
}
