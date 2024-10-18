package com.example.Hotel.managment.system.service;

import com.example.Hotel.managment.system.dto.OrderDTO;
import com.example.Hotel.managment.system.entity.OrderEntity;
import com.example.Hotel.managment.system.exp.AppBadException;
import com.example.Hotel.managment.system.repository.OrderRepository;
import com.example.Hotel.managment.system.util.SpringSecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {
    public final OrderRepository orderRepository;

    public OrderDTO createOrder(OrderDTO dto) {
        if (dto.getCheckOutDate().isBefore(dto.getCheckInDate())) {
            throw new IllegalStateException("Chiqish sanasi kirish sanasidan oldin bo'lishi mumkin emas.");
        }

        List<OrderEntity> existingOrders = orderRepository.findByRoomIdAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(
                dto.getRoomId(), dto.getCheckInDate(), dto.getCheckOutDate());

        if (!existingOrders.isEmpty()) {
            throw new IllegalStateException("Xona allaqachon bu vaqt uchun band.");
        }

        OrderEntity entity=new OrderEntity();

        entity.setOrderDate(LocalDateTime.now());
        entity.setProfileId(dto.getProfileId());
        entity.setCheckInDate(LocalDateTime.now());
        entity.setCheckOutDate(LocalDateTime.now());
        entity.setRoomId(dto.getRoomId());

        Duration duration = Duration.between(dto.getCheckInDate(), dto.getCheckOutDate());
        entity.setDuration((int) duration.toDays());


        orderRepository.save(entity);
        dto.setId(entity.getId());
        dto.setDuration(entity.getDuration());
        return  dto;
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


    public OrderDTO updateOrder(String id, OrderDTO dto) {
        var order=orderRepository.findById(id)
                .orElse(null);
        if (order==null) {
            throw new AppBadException("order not found");
        }
        OrderEntity entity=new OrderEntity();
        entity.setRoomId(dto.getRoomId());
        entity.setCheckInDate(dto.getCheckInDate());
        entity.setCheckOutDate(dto.getCheckOutDate());
        //orderRepository.save(entity);
        return toDTO(order);
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