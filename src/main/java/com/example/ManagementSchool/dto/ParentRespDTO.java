package com.example.ManagementSchool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentRespDTO {
    private Integer parentId;
    private String firstname;
    private String lastname;
    private String address;
    private String phoneNumber;
    private String profession;
}
