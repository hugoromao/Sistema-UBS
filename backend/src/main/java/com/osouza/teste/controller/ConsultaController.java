package com.osouza.teste.controller;

import java.util.List;

import com.osouza.teste.annotations.ExceptionHandlerMessage;
import com.osouza.teste.domain.entity.Usuario;
import com.osouza.teste.service.exception.ConsultaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.osouza.teste.domain.entity.Consulta;
import com.osouza.teste.service.ConsultaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/consulta")
public class ConsultaController extends BaseController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @GetMapping("{id}")
    public Consulta consulta(@PathVariable("id") Long id) {
        return consultaService.findById(id);
    }


    @ExceptionHandlerMessage(message = ConsultaException.CONSULTA_ERRO_CRIAR)
    @PostMapping("/criar")
    public ResponseEntity<?> criar(@Valid @RequestBody Consulta consulta) {
        consultaService.salvar(consulta);
        return ResponseEntity.ok(consulta);
    }

    @ExceptionHandlerMessage(message = ConsultaException.CONSULTA_ERRO_ATUALIZAR)
    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizar(@RequestBody Consulta consulta) {
        Consulta consultaAtualizada = consultaService.atualizar(consulta);
        return ResponseEntity.ok(consultaAtualizada);
    }

    @ExceptionHandlerMessage(message = ConsultaException.CONSULTA_ERRO_DELETAR)
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
        Consulta consulta = consultaService.deletar(id);
        return ResponseEntity.ok(consulta);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Consulta>> listar() {
        return ResponseEntity.ok(this.consultaService.listar());
    }

}
