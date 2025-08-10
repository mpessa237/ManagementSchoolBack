package com.example.ManagementSchool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseReqDTO {
    private String courseName;
    private Double coefficient;

}
