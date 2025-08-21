package com.example.ManagementSchool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeRespDTO {
    private Integer gradeId;
    private Double value;
    private String appreciation;
    private Integer studentId;
    private Integer courseId;
}
