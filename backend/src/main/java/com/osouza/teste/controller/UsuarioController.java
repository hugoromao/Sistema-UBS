package com.osouza.teste.controller;

import com.osouza.teste.annotations.ExceptionHandlerMessage;
import com.osouza.teste.components.JwtTokenUtil;
import com.osouza.teste.domain.dto.request.UsuarioAtualizarSenhaRequestDTO;
import com.osouza.teste.domain.dto.response.UsuarioResponseDTO;
import com.osouza.teste.domain.entity.Usuario;
import com.osouza.teste.service.UsuarioService;
import com.osouza.teste.service.exception.UsuarioException;
import com.osouza.teste.utils.TokenValidationFilter;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController extends BaseController {

    private final UsuarioService usuarioService;

    private final JwtTokenUtil jwtTokenUtil;
    private final TokenValidationFilter tokenValidationFilter;

    public UsuarioController(UsuarioService usuarioService, JwtTokenUtil jwtTokenUtil, TokenValidationFilter tokenValidationFilter) {
        this.usuarioService = usuarioService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.tokenValidationFilter = tokenValidationFilter;
    }

    @GetMapping("{id}")
    public Usuario usuario(@PathVariable("id") Long id) {
        return usuarioService.findById(id);
    }


    @ExceptionHandlerMessage(message = UsuarioException.USUARIO_ERRO_CRIAR)
    @PostMapping("/criar")
    public ResponseEntity<?> criar(@Valid @RequestBody Usuario usuario) {
        usuarioService.salvar(usuario);
        return ResponseEntity.ok(usuario);
    }

    @ExceptionHandlerMessage(message = UsuarioException.USUARIO_ERRO_ATUALIZAR)
    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario) {
        Usuario usuarioAtualizado = usuarioService.atualizar(usuario);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @ExceptionHandlerMessage(message = UsuarioException.USUARIO_ERRO_DELETAR)
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
        Usuario usuario = usuarioService.deletar(id);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/listar")
    public Page<UsuarioResponseDTO> listar(Pageable pageable) {
        return usuarioService.listar(pageable);
    }

    @GetMapping("/{id}/permissoes")
    public ResponseEntity<?> usuarioPermissoes(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.findAllPermissoesByUsuarioId(id));
    }

    @ExceptionHandlerMessage(message = UsuarioException.USUARIO_ERRO_ATUALIZAR_SENHA)
    @PutMapping("/atualizar-senha")
    public ResponseEntity<?> usuarioAtualizarSenha(@RequestHeader("Authorization") String token, @Valid @RequestBody UsuarioAtualizarSenhaRequestDTO usuarioAtualizarSenhaDTO) {

        String tokenValido = tokenValidationFilter.extractTokenFromHeader(token);
        String usuario = jwtTokenUtil.getUsernameFromToken(tokenValido);
        usuarioService.atualizarSenha(usuarioAtualizarSenhaDTO, usuario);

        return ResponseEntity.status(200).build();
    }

}
