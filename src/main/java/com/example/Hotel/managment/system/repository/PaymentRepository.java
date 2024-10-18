package com.example.Hotel.managment.system.repository;

import com.example.Hotel.managment.system.entity.PaymentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends CrudRepository<PaymentEntity,String> {

    @Query("from PaymentEntity where orderId=?1")
    Optional<PaymentEntity> findOrderId(String orderId);
}
