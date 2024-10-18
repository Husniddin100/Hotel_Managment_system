package com.example.Hotel.managment.system.controller;

import com.example.Hotel.managment.system.dto.OrderDTO;
import com.example.Hotel.managment.system.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/get-byId/{id}")
    public ResponseEntity<OrderDTO>getOrderById(@PathVariable String id){
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable String id, @RequestBody OrderDTO dto){
        return ResponseEntity.ok(orderService.updateOrder(id,dto));
    }

}
