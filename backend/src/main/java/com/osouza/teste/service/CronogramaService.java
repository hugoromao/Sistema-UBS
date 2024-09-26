package com.osouza.teste.service;

import java.util.List;
import java.util.Optional;

import com.osouza.teste.domain.dto.BaseDTO;
import com.osouza.teste.domain.entity.Cronograma;
import com.osouza.teste.service.exception.CronogramaException;
import com.osouza.teste.service.exception.CronogramaException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.osouza.teste.domain.entity.Cronograma;
import com.osouza.teste.repository.CronogramaRepository;

@Service
public class CronogramaService {
    
    private final CronogramaRepository cronogramaRepository;

    public CronogramaService(CronogramaRepository cronogramaRepository) {
        this.cronogramaRepository = cronogramaRepository;
    }

    public Cronograma findById(Long id) {
        Optional<Cronograma> cronograma = cronogramaRepository.findById(id);
        if (cronograma.isEmpty()) {
            throw new CronogramaException(CronogramaException.CRONOGRAMA_NAO_EXISTE);
        }
        return cronograma.get();
    }

    public void salvar(Cronograma cronograma) {
        cronogramaRepository.save(cronograma);
    }

    public Cronograma atualizar(Cronograma cronograma) {
        if(cronograma.getId() == null) {
            throw new CronogramaException(CronogramaException.ERRO_ATUALIZAR_SEM_ID);
        }

        Optional<Cronograma> cronogramaAntigoOptional = cronogramaRepository.findById(cronograma.getId());
        if(cronogramaAntigoOptional.isEmpty()) throw new CronogramaException(CronogramaException.CRONOGRAMA_NAO_EXISTE);

        Cronograma cronogramaAntigo = cronogramaAntigoOptional.get();
        BeanUtils.copyProperties(cronograma, cronogramaAntigo, BaseDTO.getNullPropertyNames(cronograma));

        cronogramaRepository.save(cronogramaAntigo);
        return cronogramaAntigo;
    }
    
    public Cronograma deletar(Long id) {
        Cronograma cronograma = findById(id);
        cronogramaRepository.delete(cronograma);
        return cronograma;
    }

    public List<Cronograma> listar() {
        return cronogramaRepository.findAll();
    }

}
