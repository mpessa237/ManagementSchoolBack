package com.example.ManagementSchool.mapper;

import com.example.ManagementSchool.dto.ParentReqDTO;
import com.example.ManagementSchool.dto.ParentRespDTO;
import com.example.ManagementSchool.entity.Parent;
import org.springframework.stereotype.Component;

@Component
public class ParentMapper {

    public Parent toEntity(ParentReqDTO parentReqDTO){
        Parent parent = new Parent();
        parent.setFirstname(parentReqDTO.getFirstname());
        parent.setLastname(parentReqDTO.getLastname());
        parent.setAddress(parentReqDTO.getAddress());
        parent.setProfession(parentReqDTO.getProfession());
        parent.setPhoneNumber(parentReqDTO.getPhoneNumber());
        return parent;
    }

    public ParentRespDTO toDto(Parent parent){
        ParentRespDTO parentRespDTO = new ParentRespDTO();
        parentRespDTO.setParentId(parent.getParentId());
        parentRespDTO.setFirstname(parent.getFirstname());
        parentRespDTO.setLastname(parent.getLastname());
        parentRespDTO.setAddress(parent.getAddress());
        parentRespDTO.setProfession(parent.getProfession());
        parentRespDTO.setPhoneNumber(parent.getPhoneNumber());
        return parentRespDTO;
    }
}
