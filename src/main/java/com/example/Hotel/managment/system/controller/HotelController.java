package com.example.Hotel.managment.system.controller;

import com.example.Hotel.managment.system.dto.HotelDTO;
import com.example.Hotel.managment.system.dto.RoomDTO;
import com.example.Hotel.managment.system.dto.filter.HotelFilterDTO;
import com.example.Hotel.managment.system.dto.filter.RoomFilterDTO;
import com.example.Hotel.managment.system.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "hotel api list",description = "for api list hotel create update delete and hotel list")
@RequestMapping("/hotel")
public class HotelController {
    private final HotelService hotelService;

    @Operation(summary = "Api for create hotel",description = "for api create hotel only admin")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<HotelDTO>createHotel(@RequestBody HotelDTO dto){
        return ResponseEntity.ok(hotelService.createHotel(dto));
    }

    @Operation(summary = "Api update hotel",description = "for api hotel update only admin")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<HotelDTO> updateHotel(@PathVariable Integer id, @RequestBody HotelDTO dto) {
        log.info("update hotel: {}", dto);
        return ResponseEntity.ok(hotelService.updateHotel(id, dto));
    }
    @Operation(summary = "Api for delete hotel ",description = "for api delete hotel by id")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteHotel(@PathVariable Integer id) {
        log.info("delete Hotel: {}", id);
        return ResponseEntity.ok(hotelService.delete(id));
    }
    @Operation(summary = "api get hotel",description = "for api get hotel by id ")
    @GetMapping("/get-byId/{id}")
    public ResponseEntity<HotelDTO> getHotelById(@PathVariable Integer id) {
        return ResponseEntity.ok(hotelService.getById(id));
    }
    @Operation(summary = "Api get all",description = "for api all hotel list by pagination")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<PageImpl> getAllByPagination(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResponseEntity.ok(hotelService.getAllByPagination(page, size));
    }

    @PostMapping("/filter")
    public ResponseEntity<PageImpl<HotelDTO>> filter(@RequestBody HotelFilterDTO dto,
                                                    @RequestParam(value = "page", defaultValue = "1") int page,
                                                    @RequestParam(value = "size", defaultValue = "10") int size) {
        PageImpl<HotelDTO> result = hotelService.filter(dto, page, size);
        return ResponseEntity.ok(result);
    }
}
