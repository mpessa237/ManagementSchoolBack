package com.example.ManagementSchool.mapper;

import com.example.ManagementSchool.dto.ClassroomReqDTO;
import com.example.ManagementSchool.dto.ClassroomRespDTO;
import com.example.ManagementSchool.entity.Classroom;
import org.springframework.stereotype.Component;

@Component
public class ClassroomMapper {

    public Classroom toEntity(ClassroomReqDTO classroomReqDTO){
        Classroom classroom = new Classroom();
        classroom.setClassName(classroomReqDTO.getClassName());
        classroom.setCapacity(classroomReqDTO.getCapacity());
        classroom.setCycle(classroomReqDTO.getCycle());

        return classroom;
    }

    public ClassroomRespDTO toDto(Classroom classroom){
        ClassroomRespDTO classroomRespDTO = new ClassroomRespDTO();
        classroomRespDTO.setClassroomId(classroom.getClassroomId());
        classroomRespDTO.setClassName(classroom.getClassName());
        classroomRespDTO.setCapacity(classroom.getCapacity());
        classroomRespDTO.setCycle(classroom.getCycle());



        return classroomRespDTO;
    }
}
