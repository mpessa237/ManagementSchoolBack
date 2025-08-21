package com.example.ManagementSchool.mapper;

import com.example.ManagementSchool.dto.TeacherReqDTO;
import com.example.ManagementSchool.dto.TeacherRespDTO;
import com.example.ManagementSchool.entity.Course;
import com.example.ManagementSchool.entity.Teacher;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TeacherMapper {

    public Teacher toEntity(TeacherReqDTO teacherReqDTO){
        Teacher teacher = new Teacher();
        teacher.setFirstname(teacherReqDTO.getFirstname());
        teacher.setLastname(teacherReqDTO.getLastname());
        teacher.setPhoneNumber(teacherReqDTO.getPhoneNumber());
        teacher.setAddress(teacherReqDTO.getAddress());

        return teacher;
    }

    public TeacherRespDTO toDto(Teacher teacher){
        TeacherRespDTO teacherRespDTO = new TeacherRespDTO();
        teacherRespDTO.setTeacherId(teacher.getTeacherId());
        teacherRespDTO.setFirstname(teacher.getFirstname());
        teacherRespDTO.setLastname(teacher.getLastname());
        teacherRespDTO.setAddress(teacher.getAddress());
        teacherRespDTO.setPhoneNumber(teacher.getPhoneNumber());

        if (teacher.getUser() !=null){
            teacherRespDTO.setEmail(teacher.getUser().getEmail());
        }

        if (teacher.getCourses() != null) {
            teacherRespDTO.setAssignedCourses(
                    teacher.getCourses().stream()
                            .map(Course::getCourseName)
                            .collect(Collectors.toSet())
            );
        }
        return teacherRespDTO;
    }
}
