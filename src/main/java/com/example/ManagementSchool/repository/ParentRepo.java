package com.example.ManagementSchool.repository;

import com.example.ManagementSchool.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ParentRepo extends JpaRepository<Parent,Integer> {

    List<Parent> findAllByActiveTrue();

    //List<Parent> findInactiveParentsOlderThan(LocalDateTime date);

}
