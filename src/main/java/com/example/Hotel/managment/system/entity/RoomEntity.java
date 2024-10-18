package com.example.Hotel.managment.system.entity;

import com.example.Hotel.managment.system.entity.BaseEntity;
import com.example.Hotel.managment.system.enums.RoomCategory;
import com.example.Hotel.managment.system.enums.RoomStatus;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "room")
public class RoomEntity extends BaseEntity {

    @Column(name = "number")
    private Integer number;

    @Enumerated(EnumType.STRING)
    private RoomCategory category;

    @Column(name = "hotel_id")
    private Integer hotelId;

    @ManyToOne
    @JoinColumn(name = "hotel_id", updatable = false, insertable = false)
    private HotelEntity hotel;

    @Column(name = "price")
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_status")
    private RoomStatus status;

}
