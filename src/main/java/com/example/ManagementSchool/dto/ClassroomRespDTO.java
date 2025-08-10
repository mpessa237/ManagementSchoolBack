package com.example.ManagementSchool.dto;

import com.example.ManagementSchool.entity.Cycle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomRespDTO {
    private Integer classroomId;
    private String className;
    private Integer capacity;
    private Cycle cycle;
}
