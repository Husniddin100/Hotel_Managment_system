package com.example.Hotel.managment.system.service;

import com.example.Hotel.managment.system.dto.OrderDTO;
import com.example.Hotel.managment.system.entity.OrderEntity;
import com.example.Hotel.managment.system.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {
    public final OrderRepository orderRepository;

    public OrderDTO createOrder(OrderDTO dto) {
        OrderEntity entity=new OrderEntity();

        entity.setOrderDate(LocalDateTime.now());
        entity.setProfileId(dto.getProfileId());
        entity.setCheckInDate(LocalDateTime.now());
        entity.setCheckOutDate(LocalDateTime.now());
        entity.setRoomId(dto.getRoomId());
        return  null;
    }
}
