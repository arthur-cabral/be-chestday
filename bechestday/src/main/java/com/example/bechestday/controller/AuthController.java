package com.example.bechestday.controller;

import com.example.bechestday.dto.security.AccountCredentialsDTO;
import com.example.bechestday.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signin")
    public ResponseEntity Signin(@RequestBody AccountCredentialsDTO accountCredentialsDTO){
        if (CheckIfAccountCredentialsDTOParamsAreNull(accountCredentialsDTO)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        }
        var token = authService.signin(accountCredentialsDTO);
        if (token == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        }
        return ResponseEntity.ok(token);
    }

    private static boolean CheckIfAccountCredentialsDTOParamsAreNull(AccountCredentialsDTO accountCredentialsDTO) {
        return accountCredentialsDTO == null ||
                accountCredentialsDTO.getUsername() == null ||
                accountCredentialsDTO.getUsername().isBlank() ||
                accountCredentialsDTO.getPassword() == null ||
                accountCredentialsDTO.getPassword().isBlank();
    }
}
