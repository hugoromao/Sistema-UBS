package com.osouza.teste.service;

import java.util.List;
import java.util.Optional;

import com.osouza.teste.domain.dto.BaseDTO;
import com.osouza.teste.domain.dto.request.ClienteFiltroDTO;
import com.osouza.teste.domain.dto.response.UsuarioResponseDTO;
import com.osouza.teste.domain.entity.Cliente;
import com.osouza.teste.domain.entity.Usuario;
import com.osouza.teste.repository.spec.ClienteSpec;
import com.osouza.teste.service.exception.ClienteException;
import com.osouza.teste.service.exception.ClienteException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.osouza.teste.domain.entity.Cliente;
import com.osouza.teste.repository.ClienteRepository;

@Service
public class ClienteService {
    
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente findById(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isEmpty()) {
            throw new ClienteException(ClienteException.CLIENTE_NAO_EXISTE);
        }
        return cliente.get();
    }

    public void salvar(Cliente cliente) {
        this.clienteRepository.save(cliente);
    }

    public Cliente atualizar(Cliente cliente) {
        if(cliente.getId() == null) {
            throw new ClienteException(ClienteException.ERRO_ATUALIZAR_SEM_ID);
        }

        Optional<Cliente> clienteAntigoOptional = clienteRepository.findById(cliente.getId());
        if(clienteAntigoOptional.isEmpty()) throw new ClienteException(ClienteException.CLIENTE_NAO_EXISTE);

        Cliente clienteAntigo = clienteAntigoOptional.get();
        BeanUtils.copyProperties(cliente, clienteAntigo, BaseDTO.getNullPropertyNames(cliente));

        clienteRepository.save(clienteAntigo);
        return clienteAntigo;
    }
    
    public Cliente deletar(Long id) {
        Cliente cliente = findById(id);
        clienteRepository.delete(cliente);
        return cliente;
    }

    public Page<Cliente> listar(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    public List<Cliente> detalhe(ClienteFiltroDTO filtro) {
        return clienteRepository.findAll(ClienteSpec.createPredicates(filtro));
    }

}
