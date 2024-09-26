package com.osouza.teste.service;

import java.util.List;
import java.util.Optional;

import com.osouza.teste.domain.dto.BaseDTO;
import com.osouza.teste.domain.entity.Consulta;
import com.osouza.teste.service.exception.ConsultaException;
import com.osouza.teste.service.exception.ConsultaException;
import com.osouza.teste.service.exception.UsuarioException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.osouza.teste.domain.entity.Consulta;
import com.osouza.teste.repository.ConsultaRepository;


@Service
public class ConsultaService {
    
    private final ConsultaRepository consultaRepository;

    public ConsultaService(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    public Consulta findById(Long id) {
        Optional<Consulta> consulta = consultaRepository.findById(id);
        if (consulta.isEmpty()) {
            throw new UsuarioException(ConsultaException.CONSULTA_NAO_EXISTE);
        }
        return consulta.get();
    }

    public void salvar(Consulta consulta) {
        this.consultaRepository.save(consulta);
    }

    public Consulta atualizar(Consulta consulta) {
        if(consulta.getId() == null) {
            throw new ConsultaException(ConsultaException.ERRO_ATUALIZAR_SEM_ID);
        }

        Optional<Consulta> consultaAntigoOptional = consultaRepository.findById(consulta.getId());
        if(consultaAntigoOptional.isEmpty()) throw new ConsultaException(ConsultaException.CONSULTA_NAO_EXISTE);

        Consulta consultaAntigo = consultaAntigoOptional.get();
        BeanUtils.copyProperties(consulta, consultaAntigo, BaseDTO.getNullPropertyNames(consulta));

        consultaRepository.save(consultaAntigo);
        return consultaAntigo;
    }
    
    public Consulta deletar(Long id) {
        Consulta consulta = findById(id);
        consultaRepository.delete(consulta);
        return consulta;
    }

    public List<Consulta> listar() {
        return consultaRepository.findAll();
    }

}   
