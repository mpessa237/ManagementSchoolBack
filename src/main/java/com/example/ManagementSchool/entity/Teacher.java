package com.example.ManagementSchool.entity;

import com.example.ManagementSchool.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "teacher")
public class Teacher extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teacherId;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private String address;
    private Boolean isDeleted;

    @OneToMany(mappedBy = "teacher")
    private List<Course> courses = new ArrayList<>();
    @OneToMany(mappedBy = "teacher")
    private List<Classroom> classrooms = new ArrayList<>();
}
