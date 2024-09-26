package com.osouza.teste.domain.enums;

import lombok.Getter;

@Getter
public enum CorRaca {

    PRETO("Preto"),
    PARDO("Pardo"),
    AMARELO("Amarelo"),
    BRANCO("Branco"),
    INDIGENA("Indigena");

    private final String descricao;

    CorRaca(String descricao) {
        this.descricao = descricao;
    }

}
