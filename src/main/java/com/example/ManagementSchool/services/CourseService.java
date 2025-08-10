package com.example.ManagementSchool.services;

import com.example.ManagementSchool.dto.CourseReqDTO;
import com.example.ManagementSchool.dto.CourseRespDTO;
import com.example.ManagementSchool.entity.Course;
import com.example.ManagementSchool.mapper.CourseMapper;
import com.example.ManagementSchool.repository.CourseRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepo courseRepo;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepo courseRepo, CourseMapper courseMapper) {
        this.courseRepo = courseRepo;
        this.courseMapper = courseMapper;
    }

    public CourseRespDTO addCourse(CourseReqDTO courseReqDTO){
        Course course = courseMapper.toEntity(courseReqDTO);
        Course saved = courseRepo.save(course);
        return courseMapper.toDto(saved);
    }

    public Course update(Course course,Integer courseId){
        Optional<Course> courseOptional = courseRepo.findById(courseId);

        if (courseOptional.isEmpty())
            throw new EntityNotFoundException("course not found!!");
        if (course.getCourseName()!=null)
            courseOptional.get().setCourseName(course.getCourseName());
        if (course.getCoefficient()!=null)
            courseOptional.get().setCoefficient(course.getCoefficient());
        return this.courseRepo.saveAndFlush(courseOptional.get());
    }
}
