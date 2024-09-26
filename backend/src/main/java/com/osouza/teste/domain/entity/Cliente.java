package com.osouza.teste.domain.entity;

import java.time.LocalDate;
import java.util.Date;


import com.osouza.teste.domain.enums.CorRaca;
import com.osouza.teste.domain.enums.Genero;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    private String nomeSocial;

    @NotBlank
    @Size(min = 11, max = 14)
    private String cpf;

    @NotNull
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Genero genero;

    @Enumerated(EnumType.STRING)
    @NotNull
    private CorRaca corRaca;

    @Size(max = 255)
    private String profissao;

    private String escolaridade;

    private String estadoCivil;

    @Size(max = 255)
    private String naturalidade;

    @Size(max = 255)
    private String nomeMae;

    @Size(max = 255)
    private String nomePai;

    @Size(max = 15)
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "tipo_id")
    private ClienteTipo tipo;

    private Boolean ativo = true;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

}
