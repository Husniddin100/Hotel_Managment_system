package com.example.Hotel.managment.system.service;

import com.example.Hotel.managment.system.dto.OrderDTO;
import com.example.Hotel.managment.system.entity.OrderEntity;
import com.example.Hotel.managment.system.exp.AppBadException;
import com.example.Hotel.managment.system.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {
    public final OrderRepository orderRepository;

    public OrderDTO createOrder(OrderDTO dto) {
       /* OrderEntity entity=new OrderEntity();

        entity.setOrderDate(LocalDateTime.now());
        entity.setProfileId(dto.getProfileId());
        entity.setCheckInDate(LocalDateTime.now());
        entity.setCheckOutDate(LocalDateTime.now());
        entity.setRoomId(dto.getRoomId());*/
        return  null;
    }

    public OrderDTO getOrderById(String id) {
        var orders=orderRepository.findById(id).
                orElse(null);
        if (orders==null) {
            log.warn("order not found {}",id);
            throw new AppBadException("order not found");
        }
        return toDTO(orders);
    }


    public OrderDTO toDTO(OrderEntity entity) {
        OrderDTO dto=new OrderDTO();
        dto.setId(entity.getId());
        dto.setRoomId(entity.getRoomId());
        dto.setProfileId(entity.getProfileId());
        dto.setCheckInDate(entity.getCheckInDate());
        dto.setCheckOutDate(entity.getCheckOutDate());

        return dto;
    }
}