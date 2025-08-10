package com.example.ManagementSchool.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.processing.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistrationReqDTO {
    @NotEmpty(message = "firstname is mandatory")
    @NotBlank(message = "firstname is mandatory")
    private String firstname;
    @NotEmpty(message = "lastname is mandatory")
    @NotBlank(message = "lastname is mandatory")
    private String lastname;
    @NotEmpty(message = "email is mandatory")
    @NotBlank(message = "email is mandatory")
    @Email(message = "Email not well formatted")
    private String email;
    @NotEmpty(message = "password is mandatory")
    @NotBlank(message = "password is mandatory")
    private String password;
}
