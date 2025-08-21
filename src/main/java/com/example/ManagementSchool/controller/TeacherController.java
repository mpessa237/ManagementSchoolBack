package com.example.ManagementSchool.controller;

import com.example.ManagementSchool.common.ApiResponse;
import com.example.ManagementSchool.dto.TeacherReqDTO;
import com.example.ManagementSchool.dto.TeacherRespDTO;
import com.example.ManagementSchool.services.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse> registerTeacher(@Validated @RequestBody TeacherReqDTO teacherReqDTO){
        TeacherRespDTO savedTeacher = teacherService.registerTeacher(teacherReqDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse("teacher register successfully",savedTeacher));
    }
}
