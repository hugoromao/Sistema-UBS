package com.osouza.teste.service;

import com.osouza.teste.domain.dto.BaseDTO;
import com.osouza.teste.domain.entity.Consulta;
import com.osouza.teste.domain.entity.Profissional;
import com.osouza.teste.domain.entity.Profissional;
import com.osouza.teste.repository.ProfissionalRepository;
import com.osouza.teste.service.exception.ConsultaException;
import com.osouza.teste.service.exception.ProfissionalException;
import com.osouza.teste.service.exception.UsuarioException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfissionalService {

    private final ProfissionalRepository profissionalRepository;

    public ProfissionalService(ProfissionalRepository profissionalRepository) {
        this.profissionalRepository = profissionalRepository;
    }

    public Profissional findByById(Long id) {
        Optional<Profissional> profissional = profissionalRepository.findById(id);
        if (profissional.isEmpty()) {
            throw new UsuarioException(ConsultaException.CONSULTA_NAO_EXISTE);
        }
        return profissional.get();
    }

    public Profissional salvar(Profissional profissional) {
        return this.profissionalRepository.save(profissional);
    }

    public Profissional atualizar(Profissional endereco) {
        if(endereco.getId() == null) {
            throw new ProfissionalException(ProfissionalException.ERRO_ATUALIZAR_SEM_ID);
        }

        Optional<Profissional> enderecoAntigoOptional = profissionalRepository.findById(endereco.getId());
        if(enderecoAntigoOptional.isEmpty()) throw new ProfissionalException(ProfissionalException.PROFISSIONAL_NAO_EXISTE);

        Profissional enderecoAntigo = enderecoAntigoOptional.get();
        BeanUtils.copyProperties(endereco, enderecoAntigo, BaseDTO.getNullPropertyNames(endereco));

        profissionalRepository.save(enderecoAntigo);
        return enderecoAntigo;
    }
    
    public Profissional deletar(Long id) {
        Profissional profissional = findByById(id);
        profissionalRepository.delete(profissional);
        return profissional;
    }

    public List<Profissional> listar() {
        return profissionalRepository.findAll();
    }

}
