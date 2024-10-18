package com.example.Hotel.managment.system.dto;

import com.example.Hotel.managment.system.enums.RoomCategory;
import com.example.Hotel.managment.system.enums.RoomStatus;
import lombok.Data;

@Data
public class RoomFilterDTO {
    private String id;
    private String number;
    private RoomStatus status;
    private RoomCategory category;
    private Boolean price;
    private Integer hotelId;
}
