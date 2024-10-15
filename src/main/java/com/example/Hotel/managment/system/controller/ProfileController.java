package com.example.Hotel.managment.system.controller;

import com.example.Hotel.managment.system.dto.ProfileDTO;
import com.example.Hotel.managment.system.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/profile")
public class ProfileController {
    private final ProfileService profileService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-byId/{id}")
    public ResponseEntity<ProfileDTO> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(profileService.getById(id));
    }

}
