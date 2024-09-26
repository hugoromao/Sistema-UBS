package com.osouza.teste.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Endereco {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @NotBlank
    @Size(max = 9)
    private String cep;

    @NotBlank
    @Size(max = 255)
    private String rua;

    @NotBlank
    @Size(max = 255)
    private String complemento;

    @NotBlank
    @Size(max = 255)
    private String distrito;

    @NotNull
    private Integer cidade;

    @NotNull
    private Integer estado;
}