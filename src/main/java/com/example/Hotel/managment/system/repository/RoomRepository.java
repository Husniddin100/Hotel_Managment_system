package com.example.Hotel.managment.system.repository;

import com.example.Hotel.managment.system.entity.RoomEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<RoomEntity,String> {
    @Modifying
    @Transactional
    @Query("update  RoomEntity set visible='false' where id=?1")
    void updateVisible(String id);
}
