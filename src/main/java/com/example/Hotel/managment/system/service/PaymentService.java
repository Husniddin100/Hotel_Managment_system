package com.example.Hotel.managment.system.service;

import com.example.Hotel.managment.system.dto.PaymentDTO;
import com.example.Hotel.managment.system.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentDTO createPayment(PaymentDTO dto) {
        return null;

    }
}
