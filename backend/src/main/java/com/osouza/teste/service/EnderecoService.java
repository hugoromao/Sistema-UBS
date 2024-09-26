package com.osouza.teste.service;

import com.osouza.teste.domain.dto.BaseDTO;
import com.osouza.teste.domain.entity.Endereco;
import com.osouza.teste.domain.entity.Endereco;
import com.osouza.teste.repository.EnderecoRepository;
import com.osouza.teste.service.exception.EnderecoException;
import com.osouza.teste.service.exception.EnderecoException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public Endereco findById(Long id) {
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        if (endereco.isEmpty()) {
            throw new EnderecoException(EnderecoException.ENDERECO_NAO_EXISTE);
        }
        return endereco.get();
    }

    public void salvar(Endereco endereco) {
        enderecoRepository.save(endereco);
    }

    public Endereco atualizar(Endereco endereco) {
        if(endereco.getId() == null) {
            throw new EnderecoException(EnderecoException.ERRO_ATUALIZAR_SEM_ID);
        }

        Optional<Endereco> enderecoAntigoOptional = enderecoRepository.findById(endereco.getId());
        if(enderecoAntigoOptional.isEmpty()) throw new EnderecoException(EnderecoException.ENDERECO_NAO_EXISTE);

        Endereco enderecoAntigo = enderecoAntigoOptional.get();
        BeanUtils.copyProperties(endereco, enderecoAntigo, BaseDTO.getNullPropertyNames(endereco));

        enderecoRepository.save(enderecoAntigo);
        return enderecoAntigo;
    }


    public Endereco deletar(Long id) {
        Endereco endereco = findById(id);
        enderecoRepository.delete(endereco);
        return endereco;
    }

    public List<Endereco> listar() {
        return enderecoRepository.findAll();
    }

}
