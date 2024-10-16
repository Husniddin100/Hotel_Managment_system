package com.example.Hotel.managment.system.service;

import com.example.Hotel.managment.system.dto.ProfileDTO;
import com.example.Hotel.managment.system.entity.ProfileEntity;
import com.example.Hotel.managment.system.exp.AppBadException;
import com.example.Hotel.managment.system.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileDTO getById(String id) {
        var profile = profileRepository.findById(id).
                orElse(null);

        if (profile == null) {
            throw new AppBadException("profile not found");
        }
        return toDTO(profile);

    }

    public Boolean deleteProfile(String id) {
     var profile=profileRepository.findById(id).
             orElse(null);
        if (profile == null) {
            throw new AppBadException("profile not found");
         }
     //not finish
        return true;
    }

    public ProfileDTO updateProfile(String id, ProfileDTO dto) {
        var profiles =profileRepository.findById(id).
                orElse(null);
        if (profiles == null) {
            log.warn("profile not found {}",id);
            throw new AppBadException("profile not found ");
        }
        profiles.setName(dto.getName());
        profiles.setSurname(dto.getSurname());
        profiles.setEmail(dto.getEmail());
        //not finish
        return null;
    }

    private ProfileDTO toDTO(ProfileEntity profile) {
        ProfileDTO dto = new ProfileDTO();

        dto.setId(profile.getId());
        dto.setName(profile.getName());
        dto.setSurname(profile.getSurname());
        dto.setPassword(profile.getPassword());
        dto.setEmail(profile.getEmail());
        return dto;
    }
}
