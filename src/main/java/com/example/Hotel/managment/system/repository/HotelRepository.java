package com.example.Hotel.managment.system.repository;

import com.example.Hotel.managment.system.entity.HotelEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends CrudRepository<HotelEntity,Integer> {

    @Modifying
    @Transactional
    @Query("update  HotelEntity set visible='false' where id=?1")
    void updateVisible(Integer id);
}
