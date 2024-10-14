package com.example.Hotel.managment.system.repository;

import com.example.Hotel.managment.system.entity.HotelEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends CrudRepository<HotelEntity,Integer> {
}
