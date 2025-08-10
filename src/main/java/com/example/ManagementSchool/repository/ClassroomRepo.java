package com.example.ManagementSchool.repository;

import com.example.ManagementSchool.entity.Classroom;
import com.example.ManagementSchool.entity.Cycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassroomRepo extends JpaRepository<Classroom,Integer> {

    List<Classroom> findByCycle(Cycle cycle);

    Optional<Classroom> findByClassName(String className);



}
