package com.example.ManagementSchool.services;

import com.example.ManagementSchool.dto.ParentReqDTO;
import com.example.ManagementSchool.dto.ParentRespDTO;
import com.example.ManagementSchool.entity.Parent;
import com.example.ManagementSchool.entity.Role;
import com.example.ManagementSchool.entity.User;
import com.example.ManagementSchool.mapper.ParentMapper;
import com.example.ManagementSchool.repository.ParentRepo;
import com.example.ManagementSchool.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ParentService {

    private final UserRepo userRepo;
    private final ParentRepo parentRepo;
    private final ParentMapper parentMapper;
    private final PasswordEncoder passwordEncoder;

    public ParentService(UserRepo userRepo, ParentRepo parentRepo, ParentMapper parentMapper, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.parentRepo = parentRepo;
        this.parentMapper = parentMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    public ParentRespDTO registerParent(ParentReqDTO parentReqDTO) {

        Parent parent = parentMapper.toEntity(parentReqDTO);
        Parent savedParent = parentRepo.save(parent);


        User user = new User();
        user.setFirstname(savedParent.getFirstname());
        user.setLastname(savedParent.getLastname());

        String email = generateUniqueEmail(parentReqDTO.getFirstname(), parentReqDTO.getLastname());
        user.setEmail(email);

        String temporaryPassword = generateRandomPassword(12);
        user.setPassword(passwordEncoder.encode(temporaryPassword));

        user.setEnabled(true);
        user.setAccountLocked(false);
        user.setRoles(Set.of(Role.ROLE_PARENT));

        userRepo.save(user);

        return parentMapper.toDto(savedParent);
    }

    private String generateUniqueEmail(String firstname, String lastname) {
        String baseEmail = (firstname + "." + lastname).toLowerCase().replace(" ", "") + "@address.com";
        String uniqueEmail = baseEmail;
        int counter = 1;
        while (userRepo.findByEmail(uniqueEmail).isPresent()) {
            uniqueEmail = (firstname + "." + lastname).toLowerCase().replace(" ", "") + counter + "@address.com";
            counter++;
        }
        return uniqueEmail;
    }

    private String generateRandomPassword(int length) {
        SecureRandom random = new SecureRandom();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
        return IntStream.range(0, length)
                .mapToObj(i -> String.valueOf(characters.charAt(random.nextInt(characters.length()))))
                .collect(Collectors.joining());
    }

    public List<Parent> findActiveParents(){
        return parentRepo.findAllByActiveTrue();
    }

    public void deactiveParent(Integer parentId){
        parentRepo.findById(parentId).ifPresent(parent -> {
            parent.setActive(false);
            parentRepo.save(parent);
        });
    }

}
