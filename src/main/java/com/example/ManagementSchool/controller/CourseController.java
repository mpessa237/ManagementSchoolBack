package com.example.ManagementSchool.controller;

import com.example.ManagementSchool.common.ApiResponse;
import com.example.ManagementSchool.dto.CourseReqDTO;
import com.example.ManagementSchool.dto.CourseRespDTO;
import com.example.ManagementSchool.entity.Course;
import com.example.ManagementSchool.services.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse> save(@Validated @RequestBody CourseReqDTO courseReqDTO){
        CourseRespDTO savedCourse = courseService.addCourse(courseReqDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("saved successfully",savedCourse));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{courseId}")
    public ResponseEntity<Course> update(@RequestBody Course course,@PathVariable Integer courseId){
        course.setCourseId(courseId);
        Course updateCourse = courseService.update(course, courseId);
        if (updateCourse==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updateCourse);
    }


}
