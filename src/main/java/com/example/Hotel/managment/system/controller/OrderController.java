package com.example.Hotel.managment.system.controller;

import com.example.Hotel.managment.system.dto.OrderDTO;
import com.example.Hotel.managment.system.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<OrderDTO>createOrder(@RequestBody OrderDTO dto){
        return ResponseEntity.ok(orderService.createOrder(dto));
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/get-byId/{id}")
    public ResponseEntity<OrderDTO>getOrderById(@PathVariable String id){
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable String id, @RequestBody OrderDTO dto){
        return ResponseEntity.ok(orderService.updateOrder(id,dto));
    }
    @Operation(summary = "Api get all order", description = "for api get all order by pagination sorted duration")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<PageImpl> getAllByPagination(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResponseEntity.ok(orderService.getAllByPagination(page, size));
    }

}
