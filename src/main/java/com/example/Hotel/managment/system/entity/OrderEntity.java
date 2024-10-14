package com.example.Hotel.managment.system.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "user_id")
    private String userId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private ProfileEntity user;

    @Column(name ="room_id")
    private String roomId;

    @ManyToOne
    @JoinColumn(name = "room_id",insertable = false,updatable = false)
    private RoomEntity room;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "checkin_date")
    private LocalDateTime checkInDate;

    @Column()
    private LocalDateTime checkOutDate;
}
