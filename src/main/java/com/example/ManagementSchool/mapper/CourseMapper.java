package com.example.ManagementSchool.mapper;

import com.example.ManagementSchool.dto.CourseReqDTO;
import com.example.ManagementSchool.dto.CourseRespDTO;
import com.example.ManagementSchool.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public Course toEntity(CourseReqDTO courseReqDTO){
        Course course = new Course();
        course.setCourseName(courseReqDTO.getCourseName());
        course.setCoefficient(courseReqDTO.getCoefficient());
        return course;
    }

    public CourseRespDTO toDto(Course course){
        CourseRespDTO courseRespDTO = new CourseRespDTO();
        courseRespDTO.setCourseId(course.getCourseId());
        courseRespDTO.setCourseName(course.getCourseName());
        courseRespDTO.setCoefficient(course.getCoefficient());
        return courseRespDTO;
    }
}
