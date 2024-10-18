package com.example.Hotel.managment.system.dto.filter;

import com.example.Hotel.managment.system.enums.RoomCategory;
import com.example.Hotel.managment.system.enums.RoomStatus;
import lombok.Data;

@Data
public class RoomFilterDTO {
    private String id;
    private Integer number;
    private RoomStatus status;
    private RoomCategory category;
    private Double price;
    private Integer hotelId;
}
