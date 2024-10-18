package com.example.Hotel.managment.system.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDTO {
    private String id;
    private String profileId;
    private String roomId;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private Integer duration;

}
