package com.example.Hotel.managment.system.service;

import com.example.Hotel.managment.system.dto.ProfileDTO;
import com.example.Hotel.managment.system.entity.ProfileEntity;
import com.example.Hotel.managment.system.exp.AppBadException;
import com.example.Hotel.managment.system.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
