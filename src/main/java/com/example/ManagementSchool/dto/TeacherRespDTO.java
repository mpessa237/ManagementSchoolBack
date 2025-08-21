package com.example.ManagementSchool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherRespDTO {
    private Integer teacherId;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String address;
    private String email;
    private String temporaryPassword;
    private Set<String> assignedCourses;


}
