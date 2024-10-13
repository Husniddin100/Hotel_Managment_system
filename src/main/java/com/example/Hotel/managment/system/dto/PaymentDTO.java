package com.example.Hotel.managment.system.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentDTO {
    private String id;
    private Long orderId;
    private BigDecimal amount;
    private String status;
    private LocalDateTime paymentDate;
}
