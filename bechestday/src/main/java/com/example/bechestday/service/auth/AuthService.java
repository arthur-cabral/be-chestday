package com.example.bechestday.service.auth;

import com.example.bechestday.dto.security.AccountCredentialsDTO;
import com.example.bechestday.dto.security.TokenDTO;
import com.example.bechestday.dto.security.UserDTO;

public interface AuthService {
    TokenDTO signin(AccountCredentialsDTO accountCredentialsDTO);
    TokenDTO register(UserDTO userDTO) throws Exception;
    TokenDTO refreshToken(String username, String refreshToken);
}
