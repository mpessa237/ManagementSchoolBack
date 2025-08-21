package com.example.ManagementSchool.entity;

import com.example.ManagementSchool.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @Column(unique = true, nullable = false)
    private String registrationNumber;
    private boolean active = true;

    @ManyToOne
    private Parent parent;
    @ManyToOne
    @JsonIgnoreProperties("students") //pour la reference circulaire
    private Classroom classroom;
    @OneToMany(mappedBy = "student")
    private List<Grade> grades = new ArrayList<>();
}
