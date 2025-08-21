package com.example.ManagementSchool.mapper;

import com.example.ManagementSchool.dto.StudentReqDTO;
import com.example.ManagementSchool.dto.StudentRespDTO;
import com.example.ManagementSchool.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public Student toEntity(StudentReqDTO studentReqDTO){
        Student student = new Student();
        student.setFirstname(studentReqDTO.getFirstname());
        student.setLastname(studentReqDTO.getLastname());
        student.setGender(studentReqDTO.getGender());
        student.setDateOfBirth(studentReqDTO.getDateOfBirth());
        return student;
    }

    public StudentRespDTO toDto(Student student){
        StudentRespDTO studentRespDTO = new StudentRespDTO();
        studentRespDTO.setStudentId(student.getStudentId());
        studentRespDTO.setFirstname(student.getFirstname());
        studentRespDTO.setLastname(student.getLastname());
        studentRespDTO.setDateOfBirth(student.getDateOfBirth());
        studentRespDTO.setGender(student.getGender());
        studentRespDTO.setRegistrationNumber(student.getRegistrationNumber());

        if (student.getClassroom() != null) {
            studentRespDTO.setClassroomId(student.getClassroom().getClassroomId());
            studentRespDTO.setClassName(student.getClassroom().getClassName());
        } else {
            studentRespDTO.setClassroomId(null);
            studentRespDTO.setClassName(null);
        }

        return studentRespDTO;
    }
}
