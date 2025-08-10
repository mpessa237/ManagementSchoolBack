package com.example.ManagementSchool.controller;

import com.example.ManagementSchool.common.ApiResponse;
import com.example.ManagementSchool.dto.AuthenticationReqDTO;
import com.example.ManagementSchool.dto.AuthenticationRespDTO;
import com.example.ManagementSchool.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @PostMapping("/authenticate")
    public ResponseEntity<ApiResponse> authenticate(@Validated @RequestBody AuthenticationReqDTO authenticationReqDTO){

        AuthenticationRespDTO authenticationRespDTO = authenticationService.authenticate(authenticationReqDTO);
        return ResponseEntity.ok(new ApiResponse("authenticated successfully",authenticationRespDTO));
    }
}
