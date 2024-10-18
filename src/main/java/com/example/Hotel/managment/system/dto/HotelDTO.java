package com.example.Hotel.managment.system.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class HotelDTO {
    private Integer id;
    private String name;
    private String address;
    private String description;
    private LocalDateTime createdDate;
    private List<RoomDTO> rooms;
}
