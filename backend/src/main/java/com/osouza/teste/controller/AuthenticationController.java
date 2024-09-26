package com.osouza.teste.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.osouza.teste.components.JwtTokenUtil;
import com.osouza.teste.domain.model.AuthenticationRequest;
import com.osouza.teste.domain.model.AuthenticationResponse;
import com.osouza.teste.service.UsuarioDetailsService;

import jakarta.validation.Valid;

@RestController
public class AuthenticationController extends BaseController {
    
    private final AuthenticationManager authenticationManager;

    private final UsuarioDetailsService usuarioDetailsService;

    private final JwtTokenUtil jwtTokenUtil;

    public AuthenticationController(AuthenticationManager authenticationManager, UsuarioDetailsService usuarioDetailsService, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.usuarioDetailsService = usuarioDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody AuthenticationRequest authenticationRequest, BindingResult r) {

        final UserDetails usuarioDetails = usuarioDetailsService.loadUserByUsername(authenticationRequest.getLogin());
        final String token = jwtTokenUtil.generateToken(usuarioDetails);
        return ResponseEntity.ok(new AuthenticationResponse(token));

    }

}
