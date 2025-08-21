package com.example.ManagementSchool.services;

import com.example.ManagementSchool.dto.GradeReqDTO;
import com.example.ManagementSchool.dto.GradeRespDTO;
import com.example.ManagementSchool.entity.Course;
import com.example.ManagementSchool.entity.Grade;
import com.example.ManagementSchool.entity.Student;
import com.example.ManagementSchool.mapper.GradeMapper;
import com.example.ManagementSchool.repository.CourseRepo;
import com.example.ManagementSchool.repository.GradeRepo;
import com.example.ManagementSchool.repository.StudentRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GradeService {
    private final GradeRepo gradeRepo;
    private final GradeMapper gradeMapper;
    private final CourseRepo courseRepo;
    private final StudentRepo studentRepo;

    public GradeService(GradeRepo gradeRepo, GradeMapper gradeMapper, CourseRepo courseRepo, StudentRepo studentRepo) {
        this.gradeRepo = gradeRepo;
        this.gradeMapper = gradeMapper;
        this.courseRepo = courseRepo;
        this.studentRepo = studentRepo;
    }

    @Transactional
    public GradeRespDTO save(GradeReqDTO gradeReqDTO){

        Student student = studentRepo.findById(gradeReqDTO.getStudentId())
                .orElseThrow(()->new EntityNotFoundException("student not found!!"));

        Course course = courseRepo.findById(gradeReqDTO.getCourseId())
                .orElseThrow(()->new EntityNotFoundException("course not found!!"));

        Optional<Grade> gradeOptional = gradeRepo.findByStudentAndCourse(student,course);

        Grade grade;
        if (gradeOptional.isPresent()){
            grade = gradeOptional.get();
            //update this grade
            grade.setValue(gradeReqDTO.getValue());
        }
        else {
            grade = gradeMapper.toEntity(gradeReqDTO);
            grade.setStudent(student);
            grade.setCourse(course);
        }


        if (gradeReqDTO.getValue() < 0 || gradeReqDTO.getValue() > 20){
            throw new IllegalArgumentException("The score must be between 0 and 20");
        }

        Grade savedGrade = gradeRepo.save(grade);
        return gradeMapper.toDto(savedGrade);
    }
}
