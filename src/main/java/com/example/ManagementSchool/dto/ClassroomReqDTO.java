package com.example.ManagementSchool.dto;

import com.example.ManagementSchool.entity.Cycle;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomReqDTO {
    @NotEmpty(message = "className is mandatory")
    private String className;
    private Integer capacity;
    private Cycle cycle;
}
