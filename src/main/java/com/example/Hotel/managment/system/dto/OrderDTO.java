package com.example.Hotel.managment.system.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDTO {
    private String id;
    private Long userId;
    private Long roomId;
    private LocalDateTime orderDate;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
}
