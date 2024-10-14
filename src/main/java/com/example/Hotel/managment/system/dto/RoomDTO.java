package com.example.Hotel.managment.system.dto;

import com.example.Hotel.managment.system.enums.RoomCategory;
import lombok.Data;


@Data
public class RoomDTO {
    private String id;
    private String number;
    private RoomCategory category;
    private Integer hotelId;
    private Double price;

}
