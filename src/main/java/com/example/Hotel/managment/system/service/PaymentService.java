package com.example.Hotel.managment.system.service;

import com.example.Hotel.managment.system.dto.PaymentDTO;
import com.example.Hotel.managment.system.entity.PaymentEntity;
import com.example.Hotel.managment.system.exp.AppBadException;
import com.example.Hotel.managment.system.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentDTO createPayment(PaymentDTO dto) {
        PaymentEntity entity = new PaymentEntity();

        entity.setAmount(dto.getAmount());
        entity.setStatus(dto.getStatus());
        entity.setPaymentDate(LocalDateTime.now());
        entity.setOrderId(dto.getOrderId());

        paymentRepository.save(entity);
        log.info("Payment created  {}",entity.getId());
        dto.setId(entity.getId());

        return dto;
    }

    public PaymentDTO getById(String id) {
        var payment = paymentRepository.findById(id).
                orElse(null);
           if (payment == null) {
               log.warn("Payment with id {} not found", id);
               throw new AppBadException("payment not found");
           }
           return toDTO(payment);
    }

    public PaymentDTO toDTO(PaymentEntity entity) {
        PaymentDTO dto = new PaymentDTO();
        dto.setId(entity.getId());
        dto.setAmount(entity.getAmount());
        dto.setStatus(entity.getStatus());
        dto.setPaymentDate(entity.getPaymentDate());
        dto.setOrderId(entity.getOrderId());
        return dto;
    }
}
