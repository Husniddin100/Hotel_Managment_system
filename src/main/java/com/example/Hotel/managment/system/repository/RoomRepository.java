package com.example.Hotel.managment.system.repository;

import com.example.Hotel.managment.system.entity.RoomEntity;
import com.example.Hotel.managment.system.enums.RoomStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<RoomEntity,String>
        ,PagingAndSortingRepository<RoomEntity,String> {

    @Modifying
    @Transactional
    @Query("UPDATE RoomEntity r SET r.status = :status WHERE r.id = :roomId")
    void updateStatus(@Param("roomId") String roomId, @Param("status") RoomStatus status);
}


