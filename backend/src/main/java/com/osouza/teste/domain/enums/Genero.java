package com.osouza.teste.domain.enums;

import lombok.Getter;

@Getter
public enum Genero {

    MASCULINO("Masculino"),
    FEMININO("Feminino");

    private final String descricao;

    Genero(String descricao) {
        this.descricao = descricao;
    }

}
