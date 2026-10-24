package com.fa.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fa.auth.dto.LoginRequest;
import com.fa.auth.service.JwtService;

@RestController
@RequestMapping("/auth")

public class AuthController {

    private final JwtService jwtService;
    
    public AuthController(JwtService jwtService) {
		this.jwtService=jwtService;
	}

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request) {

        if ("admin".equals(request.getUsername())
                && "password".equals(request.getPassword())) {

            String token =
                    jwtService.generateToken(
                            request.getUsername());

            return ResponseEntity.ok(token);
        }

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .build();
    }
}
