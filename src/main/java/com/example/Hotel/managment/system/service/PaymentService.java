package com.example.Hotel.managment.system.service;

import com.example.Hotel.managment.system.dto.PaymentDTO;
import com.example.Hotel.managment.system.entity.OrderEntity;
import com.example.Hotel.managment.system.entity.PaymentEntity;
import com.example.Hotel.managment.system.enums.PaymentStatus;
import com.example.Hotel.managment.system.exp.AppBadException;
import com.example.Hotel.managment.system.repository.OrderRepository;
import com.example.Hotel.managment.system.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public PaymentDTO createPayment(PaymentDTO dto) {
        Optional<PaymentEntity>optional=paymentRepository.findOrderId(dto.getOrderId());
        if (optional.isEmpty()){
            throw new AppBadException("order not found ");
        }
         PaymentEntity entity=optional.get();
        entity.setOrderId(dto.getOrderId());
        entity.setPaymentDate(LocalDateTime.now());
        entity.setAmount(dto.getAmount());
        // check amount order price
        entity.setStatus(PaymentStatus.PAID);

       // paymentRepository.save(entity);
        log.info("payment successfully orderId {}",dto.getOrderId());

        return toDTO(entity);
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
