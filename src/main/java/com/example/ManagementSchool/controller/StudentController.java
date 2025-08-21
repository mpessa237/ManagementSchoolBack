package com.example.ManagementSchool.controller;

import com.example.ManagementSchool.common.ApiResponse;
import com.example.ManagementSchool.dto.StudentReqDTO;
import com.example.ManagementSchool.dto.StudentRespDTO;
import com.example.ManagementSchool.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse> save(@Validated @RequestBody StudentReqDTO studentReqDTO){
        StudentRespDTO savedStudent = studentService.registerStudent(studentReqDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse("student saved successfully",savedStudent));
    }
}
