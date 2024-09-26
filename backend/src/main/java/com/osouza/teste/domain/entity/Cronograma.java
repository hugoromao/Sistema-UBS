package com.osouza.teste.domain.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;

@Getter
@Entity
public class Cronograma {
    
    @Id
    private Long id;

    @ManyToOne
    private Profissional profissional;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicial;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFinal;

}
