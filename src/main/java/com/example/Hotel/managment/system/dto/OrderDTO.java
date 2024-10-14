package com.example.Hotel.managment.system.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDTO {
    private String id;
    private String userId;
    private String roomId;
    private LocalDateTime orderDate;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private Boolean visible;
}
