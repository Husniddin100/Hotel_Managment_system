package com.example.Hotel.managment.system.controller;

import com.example.Hotel.managment.system.dto.RoomDTO;
import com.example.Hotel.managment.system.dto.RoomFilterDTO;
import com.example.Hotel.managment.system.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Tag(name = "room api list",description = "for api list room CRUD ")
@RestController
@RequestMapping("/room")
public class RoomController {
    private final RoomService roomService;

    @Operation(summary = "Api create room", description = "for api create room only admin")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<RoomDTO> createRoom(@RequestBody RoomDTO roomDTO) {
        log.info("createRoom room: {}", roomDTO);
        return ResponseEntity.ok(roomService.createRoom(roomDTO));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<RoomDTO>> getAllRoom() {
        return ResponseEntity.ok(roomService.getAll());
    }

    @Operation(summary = "Api update room", description = "for api update room by id")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<RoomDTO> updateRoom(@PathVariable String id, @RequestBody RoomDTO roomDTO) {
        log.info("updateRoom room: {}", roomDTO);
        return ResponseEntity.ok(roomService.updateRoom(id, roomDTO));
    }

    @Operation(summary = "Api for delete", description = "for api delete room by id")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteRoom(@PathVariable String id) {
        log.info("deleteRoom room: {}", id);
        return ResponseEntity.ok(roomService.delete(id));
    }

    @Operation(summary = "Api get room", description = "for api get room by id")
    @GetMapping("/get-byId/{id}")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable String id) {
        return ResponseEntity.ok(roomService.getById(id));
    }

    @Operation(summary = "Api get all room", description = "for api get all room by pagination sorted room_number")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<PageImpl> getAllByPagination(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResponseEntity.ok(roomService.getAllByPagination(page, size));
    }
    @PostMapping("/filter")
    public ResponseEntity<PageImpl<RoomDTO>> filter(@RequestBody RoomFilterDTO dto,
                                                    @RequestParam(value = "page", defaultValue = "1") int page,
                                                    @RequestParam(value = "size", defaultValue = "10") int size) {
        PageImpl<RoomDTO> result = roomService.filter(dto, page, size);
        return ResponseEntity.ok(result);
    }
}
