package com.example.ManagementSchool.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentReqDTO {
    private String firstname;
    private String lastname;
    private String gender;
    private Date dateOfBirth;
    private Integer classroomId;
}
