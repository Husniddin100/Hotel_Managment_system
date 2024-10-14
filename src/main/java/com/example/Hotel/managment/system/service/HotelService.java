package com.example.Hotel.managment.system.service;

import com.example.Hotel.managment.system.dto.HotelDTO;
import com.example.Hotel.managment.system.entity.HotelEntity;
import com.example.Hotel.managment.system.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class HotelService {
    private final HotelRepository hotelRepository;

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
}
