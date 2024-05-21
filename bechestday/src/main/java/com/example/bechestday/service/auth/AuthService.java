package com.example.bechestday.service.auth;

import com.example.bechestday.dto.security.AccountCredentialsDTO;
import com.example.bechestday.dto.security.TokenDTO;

public interface AuthService {
    TokenDTO signin(AccountCredentialsDTO accountCredentialsDTO);
}
