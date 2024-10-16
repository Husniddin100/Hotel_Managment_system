package com.example.Hotel.managment.system.repository;

import com.example.Hotel.managment.system.entity.RoomEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<RoomEntity,String>
        ,PagingAndSortingRepository<RoomEntity,String> {

}
