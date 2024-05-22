package com.example.bechestday.service.auth;

import com.example.bechestday.dto.security.AccountCredentialsDTO;
import com.example.bechestday.dto.security.TokenDTO;
import com.example.bechestday.dto.security.UserDTO;
import com.example.bechestday.exception.UserAlreadyExistsException;
import com.example.bechestday.model.Permission;
import com.example.bechestday.model.User;
import com.example.bechestday.repository.PermissionRepository;
import com.example.bechestday.repository.UserRepository;
import com.example.bechestday.security.jwt.JwtTokenProvider;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(JwtTokenProvider tokenProvider,
                           AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           PermissionRepository permissionRepository,
                           ModelMapper modelMapper,
                           PasswordEncoder passwordEncoder) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.permissionRepository = permissionRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public TokenDTO signin(AccountCredentialsDTO accountCredentialsDTO) {
        try {
            var username = accountCredentialsDTO.getUsername();
            var password = accountCredentialsDTO.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            var user = userRepository.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("Username " + username + " not found");
            }

            return tokenProvider.createAccessToken(username, user.getRoles());
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid username/password", e);
        }
    }

    @Override
    @Transactional
    public TokenDTO register(UserDTO userDTO) {
        var username = userDTO.getUserName();
        var fullname = userDTO.getFullName();
        var password = userDTO.getPassword();

        var existingUser = userRepository.findByUsername(username);
        if (existingUser != null) {
            throw new UserAlreadyExistsException("User with username " + username + " already exists");
        }

        User newUser = modelMapper.map(userDTO, User.class);
        newUser.setPassword(passwordEncoder.encode(password));

        Permission adminPermission = permissionRepository.save(new Permission("admin"));
        newUser.setPermissions(List.of(adminPermission));

        userRepository.save(newUser);

        return tokenProvider.createAccessToken(username, newUser.getRoles());
    }

    @Override
    public TokenDTO refreshToken(String username, String refreshToken) {
        var user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username " + username + " not found");
        }

        return tokenProvider.refreshToken(refreshToken);
    }
}