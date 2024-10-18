package com.example.Hotel.managment.system.controller;


import com.example.Hotel.managment.system.dto.AuthDTO;
import com.example.Hotel.managment.system.dto.ForgotPasswordRequestDTO;
import com.example.Hotel.managment.system.dto.ProfileDTO;
import com.example.Hotel.managment.system.dto.RegistrationDTO;
import com.example.Hotel.managment.system.entity.ProfileEntity;
import com.example.Hotel.managment.system.service.AuthService;
import com.example.Hotel.managment.system.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Tag(name = "authorization api list",description = "for api list login and registration")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final ProfileService profileService;

    @Operation(summary = "Api for login",description = "for api profile login")
    @PostMapping("/login")
    public ResponseEntity<ProfileDTO> login(@RequestBody AuthDTO authDTO) {
        log.info("login {}", authDTO.getEmail());
        return ResponseEntity.ok(authService.login(authDTO));
    }
    @Operation(summary = "Api for registration",description = "for api user registration")
    @PostMapping("/registration")
    public ResponseEntity<Boolean> registration(@RequestBody RegistrationDTO dto) {
        log.info("registration: {}", dto.getEmail());
        return ResponseEntity.ok(authService.registration(dto));
    }
    @Operation(summary = "Api for verification",description = "for api user verification")
    @GetMapping("/verification/email/{jwt}")
    public ResponseEntity<String> emailVerification(@PathVariable("jwt") String jwt){
        return ResponseEntity.ok(authService.emailVerification(jwt));
    }

}
