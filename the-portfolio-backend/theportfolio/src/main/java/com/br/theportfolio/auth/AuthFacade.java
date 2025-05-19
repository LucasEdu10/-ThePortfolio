package com.br.theportfolio.auth;

import com.br.theportfolio.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthFacade {

    private final JwtUtil jwtUtil;
    private final HttpServletRequest request;

    public AuthFacade(JwtUtil jwtUtil, HttpServletRequest request) {
        this.jwtUtil = jwtUtil;
        this.request = request;
    }

    /** Retorna o nome de usuário (subject) do token presente no header. */
    public String getCurrentUsername() {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) return null;
        String token = authHeader.substring(7);
        if (!jwtUtil.validateToken(token)) return null;
        return jwtUtil.getUsernameFromToken(token);
    }

    public String getUsernameIfValid(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) return null;
        String token = authorizationHeader.substring(7);
        return jwtUtil.validateToken(token) ? jwtUtil.getUsernameFromToken(token) : null;
    }

}
