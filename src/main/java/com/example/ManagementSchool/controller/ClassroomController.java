package com.example.ManagementSchool.controller;

import com.example.ManagementSchool.common.ApiResponse;
import com.example.ManagementSchool.dto.ClassroomReqDTO;
import com.example.ManagementSchool.dto.ClassroomRespDTO;
import com.example.ManagementSchool.entity.Classroom;
import com.example.ManagementSchool.services.ClassroomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/classroom")
public class ClassroomController {
    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse> save(@Validated @RequestBody ClassroomReqDTO classroomReqDTO){
        ClassroomRespDTO savedClassroom = classroomService.addClassroom(classroomReqDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("classroom saved successfully",savedClassroom));
    }

    @GetMapping
    public ResponseEntity<List<Classroom>> getAllClassroom(){
        return ResponseEntity.ok(this.classroomService.getAllClassroom());
    }

    @GetMapping("/premier-cycle")
    public ResponseEntity<List<Classroom>> getFirstCycleClassroom(){
        List<Classroom> classroom = classroomService.getFirstCycleClassroom();
        return ResponseEntity.ok(classroom);
    }

    @GetMapping("/second-cycle")
    public ResponseEntity<List<Classroom>> getSecondCycleClassroom(){
        List<Classroom> classroom = classroomService.getSecondCycleClassroom();
        return ResponseEntity.ok(classroom);
    }

    @PutMapping("/{classroomId}")
    public ResponseEntity<Classroom> update(@RequestBody Classroom classroom,@PathVariable Integer classroomId){
        classroom.setClassroomId(classroomId);
        Classroom updateClassroom = classroomService.updateClassroom(classroom, classroomId);

        if (updateClassroom==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updateClassroom);
    }

    @DeleteMapping("/{classroomId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer classroomId){
        classroomService.deleteClassroom(classroomId);
        return ResponseEntity.ok(new ApiResponse("classroom delete successfully",null));
    }
}
