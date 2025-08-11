package com.example.ManagementSchool.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentReqDTO {

    @NotEmpty(message = "Firstname is mandatory")
    @NotBlank(message = "Firstname is mandatory")
    private String firstname;
    private String lastname;
    @NotEmpty(message = "Address is mandatory")
    @NotBlank(message = "Address is mandatory")
    private String address;
    private String phoneNumber;
    private String profession;
}
