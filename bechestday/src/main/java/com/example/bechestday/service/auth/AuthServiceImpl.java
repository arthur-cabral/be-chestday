package com.example.bechestday.service.auth;

import com.example.bechestday.dto.security.AccountCredentialsDTO;
import com.example.bechestday.dto.security.TokenDTO;
import com.example.bechestday.repository.UserRepository;
import com.example.bechestday.security.jwt.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    private final JwtTokenProvider tokenProvider;

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    public AuthServiceImpl(JwtTokenProvider tokenProvider, AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    public TokenDTO signin(AccountCredentialsDTO accountCredentialsDTO){
        try{
            var username = accountCredentialsDTO.getUsername();
            var password = accountCredentialsDTO.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            var user = userRepository.findByUsername(username);
            var tokenResponse = new TokenDTO();
            if (user != null){
                tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
            } else {
                throw new UsernameNotFoundException("Username " + username + " not found");
            }
            return tokenResponse;
        } catch (Exception e){
            throw new BadCredentialsException("Invalid username/password");
        }
    }
}
