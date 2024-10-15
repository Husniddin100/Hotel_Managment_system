package com.example.Hotel.managment.system.service;

import com.example.Hotel.managment.system.dto.RoomDTO;
import com.example.Hotel.managment.system.entity.RoomEntity;
import com.example.Hotel.managment.system.enums.RoomStatus;
import com.example.Hotel.managment.system.exp.AppBadException;
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
        entity.setStatus(RoomStatus.EMPTY);
        entity.setHotelId(dto.getHotelId());

        log.info("create room {}", dto.getNumber());
        roomRepository.save(entity);
        dto.setId(entity.getId());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public List<RoomDTO> getAll() {
        var rooms = roomRepository.findAll();

        List<RoomDTO> roomDTOS = new ArrayList<>();
        for (RoomEntity room : rooms) {
            if (room.getVisible().equals(true)){
                roomDTOS.add(toDTO(room));
            }
        }
        return roomDTOS;
    }


    public RoomDTO updateRoom(String id, RoomDTO dto) {
        RoomEntity entity = roomRepository.findById(id)
                .orElse(null);

        if (entity == null) {
            throw new AppBadException("room not found");
        }
        entity.setCategory(dto.getCategory());
        entity.setPrice(dto.getPrice());
        entity.setNumber(dto.getNumber());

        log.info("update room {}", dto.getNumber());
        return toDTO(roomRepository.save(entity));
    }

    public Boolean delete(String id) {
        if (!roomRepository.existsById(id)) {
            throw new AppBadException("room not found");
        }
        log.info("delete room {}", id);
        roomRepository.updateVisible(id);
        return true;
    }

    public RoomDTO getById(String id) {
        var rooms = roomRepository.findById(id).
                orElse(null);

        if (rooms == null) {
            throw new AppBadException("room not found");
        }
        return toDTO(rooms);
    }

    public RoomDTO toDTO(RoomEntity entity) {
        RoomDTO dto = new RoomDTO();

        dto.setId(entity.getId());
        dto.setCategory(entity.getCategory());
        dto.setPrice(entity.getPrice());
        dto.setNumber(entity.getNumber());
        dto.setHotelId(entity.getHotelId());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
