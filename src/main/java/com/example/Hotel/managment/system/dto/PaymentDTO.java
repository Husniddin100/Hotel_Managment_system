package com.example.Hotel.managment.system.dto;

import com.example.Hotel.managment.system.enums.PaymentStatus;
import lombok.Data;


import java.time.LocalDateTime;

@Data
public class PaymentDTO {
    private String id;
    private String orderId;
    private Double amount;
    private PaymentStatus status;
    private LocalDateTime paymentDate;
    private Boolean visible;
}
