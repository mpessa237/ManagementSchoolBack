package com.example.ManagementSchool.services;

import com.example.ManagementSchool.dto.AuthenticationReqDTO;
import com.example.ManagementSchool.dto.AuthenticationRespDTO;
import com.example.ManagementSchool.entity.User;
import com.example.ManagementSchool.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationService(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public AuthenticationRespDTO authenticate(AuthenticationReqDTO authenticationReqDTO){

        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationReqDTO.getEmail(),
                        authenticationReqDTO.getPassword()

                )
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        var claims = new HashMap<String,Object>();
        var user = ((User)auth.getPrincipal());
        System.out.println(user);
        claims.put("fullName",user.fullName());
        //generate le JWT
        var jwt = jwtService.generateToken(claims,user);

        return  AuthenticationRespDTO.builder().token(jwt).build();

    }
}
