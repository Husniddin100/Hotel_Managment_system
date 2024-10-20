package com.example.Hotel.managment.system.service;

import com.example.Hotel.managment.system.dto.OrderDTO;
import com.example.Hotel.managment.system.dto.filter.PaginationResultDTO;
import com.example.Hotel.managment.system.dto.RoomDTO;
import com.example.Hotel.managment.system.dto.filter.RoomFilterDTO;
import com.example.Hotel.managment.system.entity.OrderEntity;
import com.example.Hotel.managment.system.entity.RoomEntity;
import com.example.Hotel.managment.system.enums.RoomStatus;
import com.example.Hotel.managment.system.exp.AppBadException;
import com.example.Hotel.managment.system.repository.RoomCustomRepository;
import com.example.Hotel.managment.system.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomCustomRepository roomCustomRepository;

    public RoomDTO createRoom(RoomDTO dto) {
        Optional<RoomEntity> room = roomRepository.findRoomNumber(dto.getHotelId(), dto.getNumber());

        if (room.isPresent()) {
            throw new AppBadException("room already created");
        }

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
        roomRepository.deleteById(id);
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

    public PageImpl getAllByPagination(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "number");

        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<RoomEntity> roomPage = roomRepository.findAll(paging);

        List<RoomEntity> entityList = roomPage.getContent();
        Long totalElements = roomPage.getTotalElements();

        List<RoomDTO> dtoList = new LinkedList<>();
        for (RoomEntity entity : entityList) {
            dtoList.add(toDTO(entity));
        }
        return new PageImpl<>(dtoList, paging, totalElements);
    }

    public PageImpl<RoomDTO> filter(RoomFilterDTO filter, int page, int size) {
        PaginationResultDTO<RoomEntity> paginationResult = roomCustomRepository.filter(filter, page, size);

        List<RoomDTO> dtoList = new LinkedList<>();
        for (RoomEntity entity : paginationResult.getList()) {
            dtoList.add(toDTO(entity));
        }
        Pageable paging = PageRequest.of(page - 1, size);
        return new PageImpl<>(dtoList, paging, paginationResult.getTotalSize());
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
