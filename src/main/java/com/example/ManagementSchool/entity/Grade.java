package com.example.ManagementSchool.entity;

import com.example.ManagementSchool.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "grade")
public class Grade extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gradeId;
    private Double value;
    private String appreciation;
    private Boolean isDeleted;

    @ManyToOne
    private Student student;
    @ManyToOne
    private Course course;
}
