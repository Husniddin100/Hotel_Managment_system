package com.example.Hotel.managment.system.controller;

import com.example.Hotel.managment.system.dto.PaymentDTO;
import com.example.Hotel.managment.system.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;


    @PostMapping("/create")
    public ResponseEntity<PaymentDTO>createPayment(@RequestBody PaymentDTO paymentDTO){
        return ResponseEntity.ok(paymentService.createPayment(paymentDTO));
    }
    @GetMapping("/get-byId")
    public ResponseEntity<PaymentDTO> getPaymentById(@RequestParam("id") String id){
        return ResponseEntity.ok(paymentService.getById(id));

    }



}
