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
@Table(name = "Course")
public class Course extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;
    private String courseName;
    private Double coefficient;
    private Boolean isDeleted;

    @ManyToOne
    private Teacher teacher;
    @OneToMany(mappedBy = "course")
    private List<Grade> grades = new ArrayList<>();
}
