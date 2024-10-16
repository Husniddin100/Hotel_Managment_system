package com.example.Hotel.managment.system.controller;

import com.example.Hotel.managment.system.dto.ProfileDTO;
import com.example.Hotel.managment.system.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/profile")
public class ProfileController {
    private final ProfileService profileService;

    @Operation(summary = "Api for get profile",description = "this api used get profile by id")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-byId/{id}")
    public ResponseEntity<ProfileDTO> getById(@PathVariable("id") String id) {
        log.info("getById {} ",id);
        return ResponseEntity.ok(profileService.getById(id));
    }
    @Operation(summary = "Api ")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
        log.info("delete profile {} ",id);
        return ResponseEntity.ok(profileService.deleteProfile(id));
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<ProfileDTO> update(@PathVariable("id") String id,
                                             @RequestBody ProfileDTO profileDTO) {
        return ResponseEntity.ok(profileService.updateProfile(id,profileDTO));
    }

}
