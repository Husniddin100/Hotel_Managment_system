package com.example.Hotel.managment.system.service;

import com.example.Hotel.managment.system.dto.RoomDTO;
import com.example.Hotel.managment.system.entity.RoomEntity;
import com.example.Hotel.managment.system.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public RoomDTO createRoom(RoomDTO dto) {
        RoomEntity entity = new RoomEntity();

        entity.setCategory(dto.getCategory());
        entity.setPrice(dto.getPrice());
        entity.setNumber(dto.getNumber());
        entity.setHotelId(dto.getHotelId());

        log.info("create room {}", dto.getNumber());
        roomRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<RoomDTO> getAll() {
     var rooms = roomRepository.findAll();

     List<RoomDTO>roomDTOS = new ArrayList<>();
     for (RoomEntity room : rooms) {
         roomDTOS.add(toDTO(room));
     }
     return roomDTOS;
    }


    public RoomDTO toDTO(RoomEntity entity) {
        RoomDTO dto=new RoomDTO();

        dto.setId(entity.getId());
        dto.setCategory(entity.getCategory());
        dto.setPrice(entity.getPrice());
        dto.setNumber(entity.getNumber());
        dto.setHotelId(entity.getHotelId());
       return dto;
    }

}
