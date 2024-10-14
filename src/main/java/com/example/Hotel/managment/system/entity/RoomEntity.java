package com.example.Hotel.managment.system.entity;

import com.example.Hotel.managment.system.enums.RoomCategory;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;


@Data
@Entity
@Table(name = "room")
public class RoomEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "number")
    private String number;

    @Enumerated(EnumType.STRING)
    private RoomCategory category;

    @Column(name = "hotel_id")
    private Integer hotelId;

    @ManyToOne
    @JoinColumn(name = "hotel_id", updatable = false, insertable = false)
    private HotelEntity hotel;

    @Column(name = "price")
    private Double price;
}
