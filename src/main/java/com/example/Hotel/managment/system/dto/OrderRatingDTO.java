package com.example.Hotel.managment.system.dto;

import io.swagger.models.auth.In;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderRatingDTO {
 private String Id;
 private String orderId;
 private Integer rating;
 private String comment;
 private LocalDateTime createdTime;
}
