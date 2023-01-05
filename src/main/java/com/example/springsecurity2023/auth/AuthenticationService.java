package com.example.springsecurity2023.auth;

import com.example.springsecurity2023.UserRepository;
import com.example.springsecurity2023.config.JwtAuthFilter;
import com.example.springsecurity2023.config.JwtService;
import com.example.springsecurity2023.user.Role;
import com.example.springsecurity2023.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.email(), authenticationRequest.password()));
        return new AuthenticationResponse(jwtService.generateToken(userRepository.findByEmail(authenticationRequest.email()).orElseThrow()));
    }

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        User user = new User();
        user.setFirstname(registerRequest.firstname());
        user.setEmail(registerRequest.email());
        user.setLastname(registerRequest.lastname());
        user.setPassword(passwordEncoder.encode(registerRequest.password()));
        user.setRole(Role.USER);
        userRepository.saveAndFlush(user);
        return new AuthenticationResponse(jwtService.generateToken(user));
    }
}
