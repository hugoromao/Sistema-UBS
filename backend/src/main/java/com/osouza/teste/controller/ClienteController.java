package com.osouza.teste.controller;

import java.util.List;

import com.osouza.teste.annotations.ExceptionHandlerMessage;
import com.osouza.teste.domain.dto.request.ClienteFiltroDTO;
import com.osouza.teste.domain.entity.ClienteTipo;
import com.osouza.teste.domain.entity.Usuario;
import com.osouza.teste.repository.ClienteTipoRepository;
import com.osouza.teste.service.exception.ClienteException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.osouza.teste.domain.entity.Cliente;
import com.osouza.teste.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController extends BaseController {
    
    private final ClienteService clienteService;

    private final ClienteTipoRepository clienteTipoRepository;

    public ClienteController(ClienteService clienteService, ClienteTipoRepository clienteTipoRepository) {
        this.clienteService = clienteService;
        this.clienteTipoRepository = clienteTipoRepository;
    }

    @GetMapping("{id}")
    public Cliente cliente(@PathVariable("id") Long id) {
        return clienteService.findById(id);
    }

    @PostMapping("/detalhe")
    public ResponseEntity<?> detalhe(@RequestBody ClienteFiltroDTO filtro) {
        return ResponseEntity.ok(clienteService.detalhe(filtro));
    }

    @ExceptionHandlerMessage(message = ClienteException.CLIENTE_ERRO_CRIAR)
    @PostMapping("/criar")
    public ResponseEntity<?> criar(@Valid @RequestBody Cliente cliente) {
        clienteService.salvar(cliente);
        return ResponseEntity.ok(cliente);
    }

    @ExceptionHandlerMessage(message = ClienteException.CLIENTE_ERRO_ATUALIZAR)
    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizar(@RequestBody Cliente cliente) {
        Cliente clienteAtualizado = clienteService.atualizar(cliente);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @ExceptionHandlerMessage(message = ClienteException.CLIENTE_ERRO_DELETAR)
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
        Cliente cliente = clienteService.deletar(id);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/listar")
    public Page<Cliente> listar(Pageable pageable) {
        return clienteService.listar(pageable);
    }

    @GetMapping("/cliente-tipos")
    public ResponseEntity<List<ClienteTipo>> listarTipos() {
        return ResponseEntity.ok(clienteTipoRepository.findAll());
    }

}
