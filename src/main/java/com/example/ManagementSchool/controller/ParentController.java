package com.example.ManagementSchool.controller;

import com.example.ManagementSchool.common.ApiResponse;
import com.example.ManagementSchool.dto.ParentReqDTO;
import com.example.ManagementSchool.dto.ParentRespDTO;
import com.example.ManagementSchool.services.ParentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/parents")
public class ParentController {

    private final ParentService parentService;

    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> save(@Validated @RequestBody ParentReqDTO parentReqDTO){
        ParentRespDTO parentRespDTO = parentService.registerParent(parentReqDTO);
        ApiResponse apiResponse = new ApiResponse("parent registered successfully",parentRespDTO);

        return ResponseEntity.ok(apiResponse);
    }
}
