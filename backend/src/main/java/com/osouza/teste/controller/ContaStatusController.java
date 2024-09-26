package com.osouza.teste.controller;

import com.osouza.teste.annotations.ExceptionHandlerMessage;
import com.osouza.teste.domain.entity.Consulta;
import com.osouza.teste.domain.entity.ContaStatus;
import com.osouza.teste.domain.entity.Usuario;
import com.osouza.teste.repository.ContaStatusRepository;
import com.osouza.teste.service.exception.ConsultaException;
import com.osouza.teste.service.exception.ContaStatusException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("conta-status")
public class ContaStatusController extends BaseController {

    private final ContaStatusRepository contaStatusRepository;

    public ContaStatusController(ContaStatusRepository contaStatusRepository) {
        this.contaStatusRepository = contaStatusRepository;
    }

    @GetMapping("{id}")
    public ContaStatus contaStatus(@PathVariable("id") Long id) {
        Optional<ContaStatus> contaStatus = contaStatusRepository.findById(id);
        return contaStatus.orElseThrow();
    }


    @ExceptionHandlerMessage(message = ContaStatusException.CONTA_STATUS_ERRO_CRIAR)
    @PostMapping("/criar")
    public ResponseEntity<?> criar(@Valid @RequestBody ContaStatus contaStatus) {
        contaStatusRepository.save(contaStatus);
        return ResponseEntity.ok(contaStatus);
    }

    @ExceptionHandlerMessage(message = ContaStatusException.CONTA_STATUS_ERRO_ATUALIZAR)
    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizar(@Valid @RequestBody ContaStatus contaStatus) {
        contaStatusRepository.save(contaStatus);
        return ResponseEntity.ok(contaStatus);
    }

    @ExceptionHandlerMessage(message = ContaStatusException.CONTA_STATUS_ERRO_DELETAR)
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
        Optional<ContaStatus> contaStatusOptional = contaStatusRepository.findById(id);
        ContaStatus contaStatus = contaStatusOptional.orElseThrow();
        contaStatusRepository.delete(contaStatus);
        return ResponseEntity.ok(contaStatus);
    }

}
