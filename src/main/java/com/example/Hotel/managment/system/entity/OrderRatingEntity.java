package com.example.Hotel.managment.system.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "order_rating")
public class OrderRatingEntity extends BaseEntity {

   @Column(name = "order_id")
   private String orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id",updatable = false,insertable = false, nullable = false)
    private OrderEntity order;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(length = 500)
    private String comment;

    @Column(nullable = false)
    private LocalDateTime createDate;
}
