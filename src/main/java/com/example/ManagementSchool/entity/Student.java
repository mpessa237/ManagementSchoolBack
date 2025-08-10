package com.example.ManagementSchool.entity;

import com.example.ManagementSchool.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "student")
public class Student extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;
    private String firstname;
    private String lastname;
    private String gender;
    private Date dateOfBirth;
    private String registrationNumber;
    private Boolean isDeleted;

    @ManyToOne
    private Parent parent;
    @ManyToOne
    private Classroom classroom;
    @OneToMany(mappedBy = "student")
    private List<Grade> grades = new ArrayList<>();
}
