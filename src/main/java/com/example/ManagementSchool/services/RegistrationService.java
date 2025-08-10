package com.example.ManagementSchool.services;

import com.example.ManagementSchool.dto.RegistrationReqDTO;
import com.example.ManagementSchool.entity.Role;
import com.example.ManagementSchool.entity.User;
import com.example.ManagementSchool.repository.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RegistrationService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(RegistrationReqDTO registrationReqDTO){


        //les infos du user
        User user = new User();
        user.setFirstname(registrationReqDTO.getFirstname());
        user.setLastname(registrationReqDTO.getLastname());
        user.setEmail(registrationReqDTO.getEmail());

        //encoder le mdp
        String rawPassword = registrationReqDTO.getPassword();
        String encodePassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodePassword);

        user.setEnabled(false);
        user.setAccountLocked(false);
        //mettre le role a Admin juste pour tester et apres remettre a user
        user.setRoles(Set.of(Role.ROLE_ADMIN));

        return userRepo.save(user);
    }

    public List<User> getAllUser(){
        return this.userRepo.findAll();
    }
}
