package com.example.Hotel.managment.system.controller;

import com.example.Hotel.managment.system.dto.PaymentDTO;
import com.example.Hotel.managment.system.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<PaymentDTO> makePayment(@RequestBody PaymentDTO paymentDTO) {
        return ResponseEntity.ok(paymentService.createPayment(paymentDTO));
    }

    @Operation(summary = "Api get payment",description = "for api get payment by id")
    @GetMapping("/get-byId/{id}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable String id){
        return ResponseEntity.ok(paymentService.getById(id));

    }
    @Operation(summary = "Api get all order", description = "for api get all payment by pagination sorted paymentDate")
    @GetMapping("/all")
    public ResponseEntity<PageImpl> getAllByPagination(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResponseEntity.ok(paymentService.getAllByPagination(page, size));
    }

}
