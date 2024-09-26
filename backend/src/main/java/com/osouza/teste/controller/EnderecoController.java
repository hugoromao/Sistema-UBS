package com.osouza.teste.controller;

import com.osouza.teste.annotations.ExceptionHandlerMessage;
import com.osouza.teste.domain.entity.Endereco;
import com.osouza.teste.domain.entity.Usuario;
import com.osouza.teste.service.EnderecoService;
import com.osouza.teste.service.exception.EnderecoException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("endereco")
public class EnderecoController extends BaseController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping("{id}")
    public Endereco endereco(@PathVariable("id") Long id) {
        return enderecoService.findById(id);
    }


    @ExceptionHandlerMessage(message = EnderecoException.ENDERECO_ERRO_CRIAR)
    @PostMapping("/criar")
    public ResponseEntity<?> criar(@Valid @RequestBody Endereco endereco) {
        enderecoService.salvar(endereco);
        return ResponseEntity.ok(endereco);
    }

    @ExceptionHandlerMessage(message = EnderecoException.ENDERECO_ERRO_ATUALIZAR)
    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizar(@Valid @RequestBody Endereco endereco) {
        Endereco enderecoAtualizada = enderecoService.atualizar(endereco);
        return ResponseEntity.ok(enderecoAtualizada);
    }

    @ExceptionHandlerMessage(message = EnderecoException.ENDERECO_ERRO_DELETAR)
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
        Endereco endereco = enderecoService.deletar(id);
        return ResponseEntity.ok(endereco);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Endereco>> listar() {
        return ResponseEntity.ok(this.enderecoService.listar());
    }
    
}
