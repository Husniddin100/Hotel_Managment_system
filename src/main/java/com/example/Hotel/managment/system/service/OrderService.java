package com.example.Hotel.managment.system.service;

import com.example.Hotel.managment.system.dto.OrderDTO;
import com.example.Hotel.managment.system.entity.OrderEntity;
import com.example.Hotel.managment.system.enums.RoomStatus;
import com.example.Hotel.managment.system.exp.AppBadException;
import com.example.Hotel.managment.system.repository.OrderRepository;
import com.example.Hotel.managment.system.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {
    public final OrderRepository orderRepository;
    private final RoomRepository roomRepository;

    public OrderDTO createOrder(OrderDTO dto) {
        var room =roomRepository.findById(dto.getRoomId()).orElse(null);
        if (room == null) {
            throw new AppBadException("room not found");
        }

        List<OrderEntity> existingOrders = orderRepository.findByRoomIdAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(
                dto.getRoomId(), dto.getCheckInDate(), dto.getCheckOutDate());

        if (!existingOrders.isEmpty()) {
            throw new IllegalStateException("The room is currently booked for this time.");
        }

        if (room.getStatus().equals(RoomStatus.BUSY)){
            throw new AppBadException("room is busy");
        }

        OrderEntity entity=new OrderEntity();

        Duration duration = Duration.between(dto.getCheckInDate(), dto.getCheckOutDate());
        double orderDuration=(double) duration.toDays();

        // Get order price and calculate duration
        double orderPrice =room.getPrice()*orderDuration;

        entity.setOrderDate(LocalDateTime.now());
        entity.setProfileId(dto.getProfileId());
        entity.setCheckInDate(LocalDateTime.now());
        entity.setCheckOutDate(LocalDateTime.now());
        entity.setRoomId(dto.getRoomId());
        entity.setDuration((int) duration.toDays());
        entity.setOrderPrice(orderPrice);

        roomRepository.updateStatus(dto.getRoomId(),RoomStatus.BUSY);
        orderRepository.save(entity);
        dto.setId(entity.getId());
        dto.setOrderPrice(entity.getOrderPrice());
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

        orderRepository.save(entity);
        return toDTO(order);
    }
    public PageImpl getAllByPagination(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "duration");

        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<OrderEntity> roomPage = orderRepository.findAll(paging);

        List<OrderEntity> entityList = roomPage.getContent();
        Long totalElements = roomPage.getTotalElements();

        List<OrderDTO> dtoList = new LinkedList<>();
        for (OrderEntity entity : entityList) {
            dtoList.add(toDTO(entity));
        }
        return new PageImpl<>(dtoList, paging, totalElements);
    }

    public OrderDTO toDTO(OrderEntity entity) {
        OrderDTO dto=new OrderDTO();
        dto.setId(entity.getId());
        dto.setRoomId(entity.getRoomId());
        dto.setProfileId(entity.getProfileId());
        dto.setCheckInDate(entity.getCheckInDate());
        dto.setCheckOutDate(entity.getCheckOutDate());
        dto.setDuration(entity.getDuration());

        return dto;
    }

}