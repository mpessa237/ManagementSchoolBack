package com.example.ManagementSchool.services;

import com.example.ManagementSchool.dto.StudentReqDTO;
import com.example.ManagementSchool.dto.StudentRespDTO;
import com.example.ManagementSchool.entity.Classroom;
import com.example.ManagementSchool.entity.Role;
import com.example.ManagementSchool.entity.Student;
import com.example.ManagementSchool.entity.User;
import com.example.ManagementSchool.mapper.StudentMapper;
import com.example.ManagementSchool.repository.ClassroomRepo;
import com.example.ManagementSchool.repository.StudentRepo;
import com.example.ManagementSchool.repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class StudentService {
    private final UserRepo userRepo;
    private final StudentRepo studentRepo;
    private final ClassroomRepo classroomRepo;
    private final PasswordEncoder passwordEncoder;
    private final StudentMapper studentMapper;

    public StudentService(UserRepo userRepo, StudentRepo studentRepo, ClassroomRepo classroomRepo, PasswordEncoder passwordEncoder, StudentMapper studentMapper) {
        this.userRepo = userRepo;
        this.studentRepo = studentRepo;
        this.classroomRepo = classroomRepo;
        this.passwordEncoder = passwordEncoder;
        this.studentMapper = studentMapper;
    }

    public StudentRespDTO registerStudent(StudentReqDTO studentReqDTO){
        String registrationNumber = generateRegistrationNumber();

        Classroom classroom = classroomRepo.findById(studentReqDTO.getClassroomId())
                .orElseThrow(()->new EntityNotFoundException("classroom not found with ID:" + studentReqDTO.getClassroomId()));

        Student student = studentMapper.toEntity(studentReqDTO);
        student.setRegistrationNumber(registrationNumber);
        student.setClassroom(classroom);

        Student savedStudent = studentRepo.save(student);

        User user = new User();
        user.setFirstname(savedStudent.getFirstname());
        user.setLastname(savedStudent.getLastname());

        user.setEmail(registrationNumber);

        String temporaryPassword = generateRandomPassword();
        user.setPassword(passwordEncoder.encode(temporaryPassword));

        user.setRoles(Set.of(Role.ROLE_STUDENT));
        user.setEnabled(true);
        user.setAccountLocked(false);
        userRepo.save(user);

        StudentRespDTO studentRespDTO = studentMapper.toDto(savedStudent);
        studentRespDTO.setRegistrationNumber(registrationNumber);
        studentRespDTO.setTemporaryPassword(temporaryPassword);

        return studentRespDTO;

    }

    private String generateRegistrationNumber() {
        String yearPrefix = String.valueOf(Year.now().getValue()).substring(2);
        String uuid = UUID.randomUUID().toString().substring(0, 4).toUpperCase();
        return "MAT" + yearPrefix + "-" + uuid;
    }

    private String generateRandomPassword() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}


