package com.example.Hotel.managment.system.controller;

import com.example.Hotel.managment.system.dto.OrderDTO;
import com.example.Hotel.managment.system.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<OrderDTO>createOrder(@RequestBody OrderDTO dto){
        return ResponseEntity.ok(orderService.createOrder(dto));
    }

    @PostMapping("/get-byId/{id}")
    public ResponseEntity<OrderDTO>getOrderById(@PathVariable String id){
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
}
