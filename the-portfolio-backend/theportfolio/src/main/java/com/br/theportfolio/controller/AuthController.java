package com.br.theportfolio.controller;

import com.br.theportfolio.utils.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/user")
    public ResponseEntity<?> login(@RequestParam String usuario, @RequestParam String senha) {
        // Aqui você pode validar de forma simples
        if ("admin".equals(usuario) && "123".equals(senha)) {
            String token = jwtUtil.generateToken(usuario);
            return ResponseEntity.ok().body("Bearer " + token);
        }
        return ResponseEntity.status(401).body("Credenciais inválidas");
    }
}
