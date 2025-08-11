package com.example.ManagementSchool.services;

import com.example.ManagementSchool.dto.ClassroomReqDTO;
import com.example.ManagementSchool.dto.ClassroomRespDTO;
import com.example.ManagementSchool.entity.Classroom;
import com.example.ManagementSchool.entity.Cycle;
import com.example.ManagementSchool.mapper.ClassroomMapper;
import com.example.ManagementSchool.repository.ClassroomRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomService {
    private final ClassroomRepo classroomRepo;
    private final ClassroomMapper classroomMapper;

    public ClassroomService(ClassroomRepo classroomRepo, ClassroomMapper classroomMapper) {
        this.classroomRepo = classroomRepo;
        this.classroomMapper = classroomMapper;
    }

    public ClassroomRespDTO addClassroom(ClassroomReqDTO classroomReqDTO){
        if (classroomRepo.findByClassName(classroomReqDTO.getClassName()).isPresent()){
            throw new RuntimeException("className already exist!!");
        }
        Classroom classroom = classroomMapper.toEntity(classroomReqDTO);
        Classroom savedClassroom = classroomRepo.save(classroom);
        return classroomMapper.toDto(savedClassroom);
    }


    public List<Classroom> getAllClassroom(){
        return this.classroomRepo.findAll();
    }

    public List<Classroom> getFirstCycleClassroom(){
        return this.classroomRepo.findByCycle(Cycle.PREMIER_CYCLE);
    }

    public List<Classroom> getSecondCycleClassroom(){
        return this.classroomRepo.findByCycle(Cycle.SECOND_CYCLE);
    }

    public Classroom updateClassroom(Classroom classroom,Integer classroomId){
        Optional<Classroom> classroomOptional = classroomRepo.findById(classroomId);

        if (classroomOptional.isEmpty())
            throw new EntityNotFoundException("classroom not found!");
        if (classroom.getClassName()!=null)
            classroomOptional.get().setClassName(classroom.getClassName());
        if (classroom.getCycle()!=null)
            classroomOptional.get().setCycle(classroom.getCycle());
        if (classroom.getCapacity()!=null)
            classroomOptional.get().setCapacity(classroom.getCapacity());

        return classroomRepo.saveAndFlush(classroomOptional.get());
    }

    public void deleteClassroom(Integer classroomId){
        classroomRepo.findById(classroomId)
                .orElseThrow(()->new EntityNotFoundException("classroom not found!"));
        classroomRepo.deleteById(classroomId);
    }

}
