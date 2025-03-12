package com.isika.projet3.SmartImplant.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isika.projet3.SmartImplant.dto.AuthResponseDTO;
import com.isika.projet3.SmartImplant.dto.LoginDTO;
import com.isika.projet3.SmartImplant.dto.RegisterDTO;
import com.isika.projet3.SmartImplant.models.Dentist;
import com.isika.projet3.SmartImplant.models.Patient;
import com.isika.projet3.SmartImplant.models.Role;
import com.isika.projet3.SmartImplant.models.User;
import com.isika.projet3.SmartImplant.repository.UserRepository;
import com.isika.projet3.SmartImplant.Security.jwt.JWTGenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/auth")

public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator jwtGenerator;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder,
            JWTGenerator jwtGenerator, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDto) {
        try {
            // 1. Vérifie si l'email existe déjà
            if (userRepository.existsByEmail(registerDto.getEmail())) {
                return ResponseEntity.badRequest().body("Email déjà utilisé");
            }

            // 2. Convertit la String en enum Role
            Role userRole;
            try {
                userRole = Role.valueOf(registerDto.getRole().name().toUpperCase());
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body("Rôle invalide");
            }

            // 3. Crée l'utilisateur selon le rôle
            User user = switch (userRole) {
                case DENTIST -> new Dentist();
                case PATIENT -> new Patient();

                default -> throw new IllegalArgumentException("Rôle non géré");
            };

            // 4. Hydrate l'utilisateur
            user.setEmail(registerDto.getEmail());
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            user.setRole(userRole);

            // 5. Sauvegarde en base
            userRepository.save(user);

            return ResponseEntity.ok("Inscription réussie");

        } catch (Exception e) {
            // ✅ Logge l'erreur complète pour le débogage
            logger.error("Erreur lors de l'inscription", e);
            return ResponseEntity.internalServerError().body("Erreur technique");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);

        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

}