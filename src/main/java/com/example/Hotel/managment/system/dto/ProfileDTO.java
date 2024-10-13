package com.example.Hotel.managment.system.dto;

import com.example.Hotel.managment.system.enums.ProfileRole;
import com.example.Hotel.managment.system.enums.ProfileStatus;
import lombok.Data;

@Data
public class ProfileDTO {
    private String id;
    private String name;
    private String surname;
    private ProfileStatus status;
    private ProfileRole role;
    private String email;
    private String password;
}
