package com.example.ManagementSchool.controller;

import com.example.ManagementSchool.common.ApiResponse;
import com.example.ManagementSchool.dto.GradeReqDTO;
import com.example.ManagementSchool.dto.GradeRespDTO;
import com.example.ManagementSchool.services.GradeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/grades")
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PostMapping
    public ResponseEntity<ApiResponse> addGrade(@Validated @RequestBody GradeReqDTO gradeReqDTO){
        GradeRespDTO savedGrade = gradeService.save(gradeReqDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse("grade added successfully",savedGrade));
    }

}
