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
@Table(name = "classroom")
public class Classroom extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer classroomId;
    private String className;
    private Integer capacity;
    private boolean active = true ;

    @Enumerated(EnumType.STRING)
    private Cycle cycle;

    @OneToMany(mappedBy = "classroom",cascade = CascadeType.ALL)
    private List<Student> students = new ArrayList<>();

}
