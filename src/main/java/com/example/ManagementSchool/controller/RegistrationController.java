package com.example.ManagementSchool.controller;

import com.example.ManagementSchool.common.ApiResponse;
import com.example.ManagementSchool.dto.RegistrationReqDTO;
import com.example.ManagementSchool.entity.User;
import com.example.ManagementSchool.services.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegistrationReqDTO registrationReqDTO){
        var user = registrationService.register(registrationReqDTO);
        return ResponseEntity.ok(new ApiResponse("successfully register user",user.getFirstname()));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok(this.registrationService.getAllUser());
    }
}
