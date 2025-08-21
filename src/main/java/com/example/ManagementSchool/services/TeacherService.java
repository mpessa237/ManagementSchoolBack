package com.example.ManagementSchool.services;

import com.example.ManagementSchool.dto.TeacherReqDTO;
import com.example.ManagementSchool.dto.TeacherRespDTO;
import com.example.ManagementSchool.entity.Course;
import com.example.ManagementSchool.entity.Role;
import com.example.ManagementSchool.entity.Teacher;
import com.example.ManagementSchool.entity.User;
import com.example.ManagementSchool.repository.CourseRepo;
import com.example.ManagementSchool.repository.TeacherRepo;
import com.example.ManagementSchool.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TeacherService {
    private final TeacherRepo teacherRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final CourseRepo courseRepo;

    public TeacherService(TeacherRepo teacherRepo, PasswordEncoder passwordEncoder, UserRepo userRepo, CourseRepo courseRepo) {
        this.teacherRepo = teacherRepo;
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.courseRepo = courseRepo;
    }

    @Transactional
    public TeacherRespDTO registerTeacher(TeacherReqDTO teacherReqDTO){
        Set<Course> courses = teacherReqDTO.getCourseIds().stream()
                .map(courseId -> courseRepo.findById(courseId)
                        .orElseThrow(() -> new RuntimeException("Le cours avec l'ID " + courseId + " n'existe pas.")))
                .collect(Collectors.toSet());

        // 3. Créer l'entité User pour le professeur
        User user = new User();
        user.setFirstname(teacherReqDTO.getFirstname());
        user.setLastname(teacherReqDTO.getLastname());
        user.setEmail(teacherReqDTO.getEmail());

        String temporaryPassword = generateRandomPassword();
        user.setPassword(passwordEncoder.encode(temporaryPassword));
        user.setRoles(Set.of(Role.ROLE_TEACHER));
        user.setEnabled(true);

        User savedUser = userRepo.save(user);

        // 4. Créer l'entité Teacher et la lier à l'entité User
        Teacher teacher = new Teacher();
        teacher.setFirstname(teacherReqDTO.getFirstname());
        teacher.setLastname(teacherReqDTO.getLastname());
        teacher.setPhoneNumber(teacherReqDTO.getPhoneNumber());
        teacher.setAddress(teacherReqDTO.getAddress());
        teacher.setCourses(courses);
        teacher.setUser(savedUser); // Lier le professeur à l'utilisateur

        Teacher savedTeacher = teacherRepo.save(teacher);

        // 5. Construire le DTO de réponse
        TeacherRespDTO teacherRespDTO = new TeacherRespDTO();
        teacherRespDTO.setTeacherId(savedTeacher.getTeacherId());
        teacherRespDTO.setFirstname(savedTeacher.getFirstname());
        teacherRespDTO.setLastname(savedTeacher.getLastname());
        teacherRespDTO.setAddress(savedTeacher.getAddress());
        teacherRespDTO.setPhoneNumber(savedTeacher.getPhoneNumber());
        teacherRespDTO.setEmail(savedUser.getEmail());
        teacherRespDTO.setTemporaryPassword(temporaryPassword);
        teacherRespDTO.setAssignedCourses(courses.stream().map(Course::getCourseName).collect(Collectors.toSet()));

        return teacherRespDTO;
    }

    private String generateRandomPassword() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
    }

