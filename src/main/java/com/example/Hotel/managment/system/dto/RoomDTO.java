package com.example.Hotel.managment.system.dto;

import lombok.Data;


@Data
public class RoomDTO {
    private Integer id;
    private String number;
    private String category;
    private Long hotelId;
}
