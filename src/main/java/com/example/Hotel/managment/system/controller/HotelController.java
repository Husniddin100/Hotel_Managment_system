package com.example.Hotel.managment.system.controller;

import com.example.Hotel.managment.system.dto.HotelDTO;
import com.example.Hotel.managment.system.dto.RoomDTO;
import com.example.Hotel.managment.system.service.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/hotel")
public class HotelController {
    private final HotelService hotelService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<HotelDTO>createHotel(@RequestBody HotelDTO dto){
        return ResponseEntity.ok(hotelService.createHotel(dto));
    }
    @GetMapping("/get-all")
    public ResponseEntity<List<HotelDTO>> getAllHotel() {
        return ResponseEntity.ok(hotelService.getAll());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<HotelDTO> updateHotel(@PathVariable Integer id, @RequestBody HotelDTO dto) {
        log.info("update hotel: {}", dto);
        return ResponseEntity.ok(hotelService.updateHotel(id, dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteHotel(@PathVariable Integer id) {
        log.info("delete Hotel: {}", id);
        return ResponseEntity.ok(hotelService.delete(id));
    }

    @GetMapping("/get-byId/{id}")
    public ResponseEntity<HotelDTO> getHotelById(@PathVariable Integer id) {
        return ResponseEntity.ok(hotelService.getById(id));
    }



}
