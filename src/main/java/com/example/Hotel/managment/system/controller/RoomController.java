package com.example.Hotel.managment.system.controller;

import com.example.Hotel.managment.system.dto.RoomDTO;
import com.example.Hotel.managment.system.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/room")
public class RoomController {
    private final RoomService roomService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<RoomDTO>createRoom(@RequestBody RoomDTO roomDTO){
        log.info("createRoom room: {}", roomDTO);
        return ResponseEntity.ok(roomService.createRoom(roomDTO));
    }
    @GetMapping("/get-all")
    public ResponseEntity<List<RoomDTO>> getAllRoom(){
        return ResponseEntity.ok(roomService.getAll());
    }
}
