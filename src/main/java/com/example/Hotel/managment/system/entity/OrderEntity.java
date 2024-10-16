package com.example.Hotel.managment.system.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

    @Column(name = "profile_id")
    private String profileId;

    @ManyToOne
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profile;

    @Column(name ="room_id")
    private String roomId;

    @ManyToOne
    @JoinColumn(name = "room_id",insertable = false,updatable = false)
    private RoomEntity room;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "checkin_date")
    private LocalDateTime checkInDate;

    @Column(name = "checkOutDate")
    private LocalDateTime checkOutDate;

}
