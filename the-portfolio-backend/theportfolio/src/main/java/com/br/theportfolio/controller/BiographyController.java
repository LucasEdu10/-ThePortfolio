package com.br.theportfolio.controller;

import com.br.theportfolio.auth.AuthFacade;
import com.br.theportfolio.model.Biography;
import com.br.theportfolio.model.Experience;
import com.br.theportfolio.service.BiographyService;
import com.br.theportfolio.service.ExperienceService;
import com.br.theportfolio.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/biografia")
public class BiographyController {

    private final BiographyService service;
    private final AuthFacade auth;

    public BiographyController(BiographyService service, AuthFacade auth) {
        this.service = service;
        this.auth = auth;
    }

    @PostMapping
    public ResponseEntity<?> createBio(@RequestBody Biography biography,
                                              @RequestHeader("Authorization") String authorizationHeader) {

        // delega a validação ao AuthFacade
        String username = auth.getUsernameIfValid(authorizationHeader);
        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Token inválido ou ausente");
        }

        return ResponseEntity.ok(service.save(biography));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBio(@RequestHeader("Authorization") String authorizationHeader,
                                    @PathVariable("id") Long id) {
        try {
            // delega a validação ao AuthFacade
            String username = auth.getUsernameIfValid(authorizationHeader);
            if (username == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Token inválido ou ausente");
            }

            var xp = service.getBio(id);
            return ResponseEntity.ok(xp);
        } catch (Exception ex) {
            /* 4. Qualquer exceção inesperada */
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao processar a requisição: " + ex.getMessage());
        }
    }

}
