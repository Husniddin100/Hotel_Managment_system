package com.example.Hotel.managment.system.service;

import com.example.Hotel.managment.system.dto.HotelDTO;
import com.example.Hotel.managment.system.dto.RoomDTO;
import com.example.Hotel.managment.system.dto.filter.HotelFilterDTO;
import com.example.Hotel.managment.system.dto.filter.PaginationResultDTO;
import com.example.Hotel.managment.system.dto.filter.RoomFilterDTO;
import com.example.Hotel.managment.system.entity.HotelEntity;
import com.example.Hotel.managment.system.entity.RoomEntity;
import com.example.Hotel.managment.system.exp.AppBadException;
import com.example.Hotel.managment.system.repository.HotelCustomRepository;
import com.example.Hotel.managment.system.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class HotelService {
    private final HotelRepository hotelRepository;
    private final RoomService roomService;
    private final HotelCustomRepository hotelCustomRepository;

    public HotelDTO createHotel(HotelDTO dto) {
        HotelEntity entity = new HotelEntity();
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setDescription(dto.getDescription());
        log.info("hotel created {}", dto.getName());

        hotelRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public HotelDTO updateHotel(Integer id, HotelDTO dto) {
        HotelEntity entity = hotelRepository.findById(id)
                .orElse(null);

        if (entity == null) {
            throw new AppBadException("hotel not found");
        }
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setDescription(dto.getDescription());
        log.info("hotel updated {}", dto.getName());

        return toDTO(hotelRepository.save(entity));
    }

    public Boolean delete(Integer id) {
        if (!hotelRepository.existsById(id)) {
            throw new AppBadException("hotel not found");
        }
        log.warn("delete hotel {}", id);
        hotelRepository.deleteById(id);
        return true;
    }

    public HotelDTO getById(Integer id) {
        var rooms = hotelRepository.findById(id).
                orElse(null);

        if (rooms == null) {
            throw new AppBadException("hotel not found");
        }
        return toDTO(rooms);
    }

    public PageImpl getAllByPagination(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");

        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<HotelEntity> roomPage = hotelRepository.findAll(paging);

        List<HotelEntity> entityList = roomPage.getContent();
        Long totalElements = roomPage.getTotalElements();

        List<HotelDTO> dtoList = new LinkedList<>();
        for (HotelEntity entity : entityList) {
            dtoList.add(toDTO(entity));
        }
        return new PageImpl<>(dtoList, paging, totalElements);
    }

    public PageImpl<HotelDTO> filter(HotelFilterDTO filter, int page, int size) {
        PaginationResultDTO<HotelEntity> paginationResult=hotelCustomRepository.filter(filter,page,size);

        List<HotelDTO>dtoList=new LinkedList<>();
        for (HotelEntity entity:paginationResult.getList()){
            dtoList.add(toDTO(entity));
        }
        Pageable paging=PageRequest.of(page-1,size);
        return new PageImpl<>(dtoList,paging,paginationResult.getTotalSize());
    }

    private HotelDTO toDTO(HotelEntity entity) {
        HotelDTO dto = new HotelDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAddress(entity.getAddress());
        dto.setDescription(entity.getDescription());

        List<RoomDTO> roomDTOList = entity.getRooms().stream()
                .map(roomService::toDTO)
                .collect(Collectors.toList());
        dto.setRooms(roomDTOList);
        return dto;
    }
}
