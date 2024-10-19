package com.example.Hotel.managment.system.controller;

import com.example.Hotel.managment.system.dto.OrderRatingDTO;
import com.example.Hotel.managment.system.service.OrderRatingService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rating")
public class OrderRatingController {
    private final OrderRatingService orderRatingService;

    @Operation(summary = "Api for rate oder",description = "this api is used to rate orders")
    @PostMapping("/rate")
    public ResponseEntity<OrderRatingDTO> createOrderRating(@RequestBody OrderRatingDTO orderRatingDTO) {
        return ResponseEntity.ok(orderRatingService.addRating(orderRatingDTO));

    }
}
