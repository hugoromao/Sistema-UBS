package com.osouza.teste.controller;

import java.util.List;

import com.osouza.teste.annotations.ExceptionHandlerMessage;
import com.osouza.teste.domain.entity.Usuario;
import com.osouza.teste.service.exception.CronogramaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.osouza.teste.domain.entity.Consulta;
import com.osouza.teste.domain.entity.Cronograma;
import com.osouza.teste.service.CronogramaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cronograma")
public class CronogramaController extends BaseController {
    
    private final CronogramaService cronogramaService;

    public CronogramaController(CronogramaService cronogramaService) {
        this.cronogramaService = cronogramaService;
    }

    @GetMapping("{id}")
    public Cronograma cronograma(@PathVariable("id") Long id) {
        return cronogramaService.findById(id);
    }


    @ExceptionHandlerMessage(message = CronogramaException.CRONOGRAMA_ERRO_CRIAR)
    @PostMapping("/criar")
    public ResponseEntity<?> criar(@Valid @RequestBody Cronograma cronograma) {
        cronogramaService.salvar(cronograma);
        return ResponseEntity.ok(cronograma);
    }

    @ExceptionHandlerMessage(message = CronogramaException.CRONOGRAMA_ERRO_ATUALIZAR)
    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizar(@RequestBody Cronograma cronograma) {
        Cronograma cronogramaAtualizada = cronogramaService.atualizar(cronograma);
        return ResponseEntity.ok(cronogramaAtualizada);
    }

    @ExceptionHandlerMessage(message = CronogramaException.CRONOGRAMA_ERRO_DELETAR)
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
        Cronograma cronograma = cronogramaService.deletar(id);
        return ResponseEntity.ok(cronograma);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Cronograma>> listar() {
        return ResponseEntity.ok(this.cronogramaService.listar());
    }

}
