package com.osouza.teste.controller;

import com.osouza.teste.annotations.ExceptionHandlerMessage;
import com.osouza.teste.domain.entity.Profissional;
import com.osouza.teste.domain.entity.Usuario;
import com.osouza.teste.service.ProfissionalService;
import com.osouza.teste.service.exception.ProfissionalException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profissional")
public class ProfissionalController extends BaseController {

    private final ProfissionalService profissionalService;

    public ProfissionalController(ProfissionalService profissionalService) {
        this.profissionalService = profissionalService;
    }

    @GetMapping("{id}")
    public Profissional profissional(@PathVariable("id") Long id) {
        return profissionalService.findByById(id);
    }


    @ExceptionHandlerMessage(message = ProfissionalException.PROFISSIONAL_ERRO_CRIAR)
    @PostMapping("/criar")
    public ResponseEntity<?> criar(@Valid @RequestBody Profissional profissional) {
        Profissional profissionalSalvo = profissionalService.salvar(profissional);
        return ResponseEntity.ok(profissionalSalvo);
    }

    @ExceptionHandlerMessage(message = ProfissionalException.PROFISSIONAL_ERRO_ATUALIZAR)
    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizar(@RequestBody Profissional profissional) {
        Profissional profissinalAtualizado = profissionalService.atualizar(profissional);
        return ResponseEntity.ok(profissinalAtualizado);
    }

    @ExceptionHandlerMessage(message = ProfissionalException.PROFISSIONAL_ERRO_DELETAR)
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
        Profissional profissional = profissionalService.deletar(id);
        return ResponseEntity.ok(profissional);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Profissional>> listar() {
        return ResponseEntity.ok(this.profissionalService.listar());
    }
    
}
