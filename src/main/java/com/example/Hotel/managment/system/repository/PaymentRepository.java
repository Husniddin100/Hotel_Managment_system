package com.example.Hotel.managment.system.repository;

import com.example.Hotel.managment.system.entity.OrderEntity;
import com.example.Hotel.managment.system.entity.PaymentEntity;
import com.example.Hotel.managment.system.enums.PaymentStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends CrudRepository<PaymentEntity,String> ,
        PagingAndSortingRepository<PaymentEntity,String> {

    @Query("from OrderEntity where id=?1")
    Optional<OrderEntity> findOrderId(String orderId);

    @Query("from PaymentEntity where orderId=?1 and status=?2")
    Optional<PaymentEntity> checkStatus(String orderId, PaymentStatus paymentStatus);
}
