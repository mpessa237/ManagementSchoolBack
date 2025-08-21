package com.example.ManagementSchool.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeReqDTO {
    @Positive(message = "Grade value should be positive")
    private Double value;
    @NotBlank(message = "Appreciation is mandatory")
    @NotEmpty(message = "Appreciation is mandatory")
    private String appreciation;
    private Integer studentId;
    private Integer courseId;
}
