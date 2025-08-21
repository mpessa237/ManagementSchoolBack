package com.example.ManagementSchool.mapper;

import com.example.ManagementSchool.dto.GradeReqDTO;
import com.example.ManagementSchool.dto.GradeRespDTO;
import com.example.ManagementSchool.entity.Grade;
import org.springframework.stereotype.Component;

@Component
public class GradeMapper {

    public Grade toEntity(GradeReqDTO gradeReqDTO){

        Grade grade = new Grade();
        grade.setValue(gradeReqDTO.getValue());
        grade.setAppreciation(gradeReqDTO.getAppreciation());
        return grade;
    }

    public GradeRespDTO toDto(Grade grade){
        GradeRespDTO gradeRespDTO = new GradeRespDTO();
        gradeRespDTO.setGradeId(grade.getGradeId());
        gradeRespDTO.setValue(grade.getValue());
        gradeRespDTO.setAppreciation(grade.getAppreciation());
        gradeRespDTO.setStudentId(grade.getStudent().getStudentId());
        gradeRespDTO.setCourseId(grade.getCourse().getCourseId());
        return gradeRespDTO;
    }
}
