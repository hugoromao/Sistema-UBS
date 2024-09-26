package com.osouza.teste.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Profissional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "conselho_id", referencedColumnName = "id")
    private Conselho conselho;

    @NotNull
    private String conselhoEstado;

    @NotNull
    private String numeroRegistro;

    @ManyToOne
    @JoinColumn(name = "especialidade_id", referencedColumnName = "id")
    private Especialidade especialidade;

    private Boolean ativo = false;

}
