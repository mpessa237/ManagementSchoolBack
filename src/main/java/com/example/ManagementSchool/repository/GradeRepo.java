package com.example.ManagementSchool.repository;

import com.example.ManagementSchool.entity.Course;
import com.example.ManagementSchool.entity.Grade;
import com.example.ManagementSchool.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GradeRepo extends JpaRepository<Grade,Integer> {

    Optional<Grade> findByStudentAndCourse(Student student, Course course);
}
