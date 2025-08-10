package com.example.ManagementSchool.repository;

import com.example.ManagementSchool.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepo extends JpaRepository<Token,Integer> {
    Optional<Token> findByToken(String token);
}
