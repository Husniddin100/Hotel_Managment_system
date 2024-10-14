package com.example.Hotel.managment.system.service;

import com.example.Hotel.managment.system.dto.HotelDTO;
import com.example.Hotel.managment.system.dto.RoomDTO;
import com.example.Hotel.managment.system.entity.HotelEntity;
import com.example.Hotel.managment.system.exp.AppBadException;
import com.example.Hotel.managment.system.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class HotelService {
    private final HotelRepository hotelRepository;
    private final RoomService roomService;

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

    public List<HotelDTO> getAll() {
        var hotel = hotelRepository.findAll();

        List<HotelDTO> hotelDTOS = new ArrayList<>();
        for (HotelEntity hotel1 : hotel) {
            hotelDTOS.add(toDTO(hotel1));
        }
        return hotelDTOS;
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
        log.info("delete hotel {}", id);

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
