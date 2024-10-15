package com.example.Hotel.managment.system.repository;

import com.example.Hotel.managment.system.entity.PaymentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends CrudRepository<PaymentEntity,String> {
}
