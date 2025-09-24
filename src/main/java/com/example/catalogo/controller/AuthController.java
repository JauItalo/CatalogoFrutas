package com.example.catalogo.controller;

import com.example.catalogo.dto.*;
import com.example.catalogo.model.Role;
import com.example.catalogo.model.User;
import com.example.catalogo.repository.RoleRepository;
import com.example.catalogo.repository.UserRepository;
import com.example.catalogo.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterDTO dto) {
        if (userRepository.existsByUsername(dto.getUsername()))
            return ResponseEntity.badRequest().body("Username em uso");

        User u = new User();
        u.setNome(dto.getNome());
        u.setUsername(dto.getUsername());
        u.setPassword(passwordEncoder.encode(dto.getPassword()));

        Role role = roleRepository.findByName("ROLE_USER").orElseGet(() -> {
            Role r = new Role();
            r.setName("ROLE_USER");
            return roleRepository.save(r);
        });

        u.setRoles(Collections.singleton(role));
        userRepository.save(u);

        return ResponseEntity.ok("Usuario registrado");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO req) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );

        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String token = jwtUtil.generateToken(userDetails);

        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(a -> a.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new LoginResponseDTO(token, req.getUsername(), roles));
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(
            @AuthenticationPrincipal org.springframework.security.core.userdetails.User user
    ) {
        if (user == null)
            return ResponseEntity.status(401).build();

        return ResponseEntity.ok(user.getUsername());
    }
}
