package com.example.Hotel.managment.system.repository;

import com.example.Hotel.managment.system.entity.OrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity,String> {
    List<OrderEntity> findByRoomIdAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(String roomId, LocalDateTime checkOutDate, LocalDateTime checkInDate);

}

