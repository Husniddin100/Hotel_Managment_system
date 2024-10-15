package com.example.Hotel.managment.system.repository;

import com.example.Hotel.managment.system.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity,String> {
}
