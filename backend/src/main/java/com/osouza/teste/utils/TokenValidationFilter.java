package com.osouza.teste.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.osouza.teste.components.JwtTokenUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TokenValidationFilter extends OncePerRequestFilter {

    private final String AUTH_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";

    private final JwtTokenUtil jwtTokenUtil;

    public TokenValidationFilter(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = extractToken(request);
        
        if (token != null && jwtTokenUtil.validateToken(token)) {
            
            String username = jwtTokenUtil.getUsernameFromToken(token);   
            List<String> roles = jwtTokenUtil.getRolesFromToken(token);
            // adicionando as permissões do usuario
            Set<GrantedAuthority> authorities = new HashSet<>();
            for(String role : roles) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
            }
            
            Authentication auth = new UsernamePasswordAuthenticationToken(
                username, null, authorities
            );

            SecurityContextHolder.getContext().setAuthentication(auth);
            response.addCookie(new Cookie("username", username.replaceAll(" ", "-")));

        }
        
        filterChain.doFilter(request, response);
    }
    
    public String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader(AUTH_HEADER);
        if (authHeader != null && authHeader.startsWith(TOKEN_PREFIX)) {
            return authHeader.replace(TOKEN_PREFIX, "");
        }
        return null;
    }

    public String extractTokenFromHeader(String authorization) {
        if (authorization != null && authorization.startsWith(TOKEN_PREFIX)) {
            return authorization.replace(TOKEN_PREFIX, "");
        }
        return null;
    }

}
