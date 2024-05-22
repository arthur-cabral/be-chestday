package com.example.bechestday.controller;

import com.example.bechestday.dto.security.AccountCredentialsDTO;
import com.example.bechestday.dto.security.UserDTO;
import com.example.bechestday.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody AccountCredentialsDTO accountCredentialsDTO){
        if (checkIfParamsAreNull(accountCredentialsDTO)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        }
        var token = authService.signin(accountCredentialsDTO);
        if (token == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        }
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserDTO userDTO) throws Exception {
        var token = authService.register(userDTO);
        if (token == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        }
        return ResponseEntity.ok(token);
    }

    private static boolean checkIfParamsAreNull(AccountCredentialsDTO accountCredentialsDTO) {
        return accountCredentialsDTO == null ||
                accountCredentialsDTO.getUsername() == null ||
                accountCredentialsDTO.getUsername().isBlank() ||
                accountCredentialsDTO.getPassword() == null ||
                accountCredentialsDTO.getPassword().isBlank();
    }

    @PutMapping("/refresh/{username}")
    public ResponseEntity refreshToken(@PathVariable("username") String username, @RequestHeader("Authorization") String refreshToken){
        if (checkIfParamsAreNull(username, refreshToken)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        }
        var token = authService.refreshToken(username, refreshToken);
        if (token == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        }
        return ResponseEntity.ok(token);
    }

    private static boolean checkIfParamsAreNull(String username, String refreshToken) {
        return refreshToken == null ||
                refreshToken.isBlank() ||
                username == null ||
                username.isBlank();
    }
}
