package com.br.theportfolio.controller;

import com.br.theportfolio.auth.AuthFacade;
import com.br.theportfolio.model.Experience;
import com.br.theportfolio.service.ExperienceService;
import com.br.theportfolio.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/experiencias")
public class ExperienceController {

    private final ExperienceService service;
    private final AuthFacade auth;
    private final JwtUtil jwtUtil;

    public ExperienceController(ExperienceService service, AuthFacade auth, JwtUtil jwtUtil) {
        this.service = service;
        this.auth = auth;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<?> createExperience(@RequestBody Experience experience,
                                              @RequestHeader("Authorization") String authorizationHeader) {

        // delega a validação ao AuthFacade
        String username = auth.getUsernameIfValid(authorizationHeader);
        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Token inválido ou ausente");
        }
        return ResponseEntity.ok(service.save(experience));
    }

    @GetMapping
    public ResponseEntity<?> getExperience(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            // delega a validação ao AuthFacade
            String username = auth.getUsernameIfValid(authorizationHeader);
            if (username == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Token inválido ou ausente");
            }

            var xp = service.getExperience();
            return ResponseEntity.ok(xp);
        } catch (Exception ex) {
            /* 4. Qualquer exceção inesperada */
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao processar a requisição: " + ex.getMessage());
        }
    }

}
