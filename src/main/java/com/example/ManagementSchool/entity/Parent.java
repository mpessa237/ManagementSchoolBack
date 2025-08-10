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
@Table(name = "parent")
public class Parent extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer parentId;
    private String firstname;
    private String lastname;
    private String address;
    private String phoneNumber;
    private String profession;

    private boolean active = true;

    @OneToMany(mappedBy = "parent",cascade = CascadeType.ALL)
    private List<Student> students = new ArrayList<>();
}
