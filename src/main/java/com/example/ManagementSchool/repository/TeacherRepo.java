package com.example.ManagementSchool.repository;

import com.example.ManagementSchool.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher,Integer> {
    List<Teacher> findAllByActiveTrue();
}
