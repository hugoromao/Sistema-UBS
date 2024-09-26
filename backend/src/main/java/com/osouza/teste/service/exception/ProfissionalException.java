package com.osouza.teste.service.exception;

public class ProfissionalException extends BaseException {

    public static final String PROFISSIONAL_NAO_EXISTE = "Não foi possível encontrar o profissional";
    public static final String PROFISSIONAL_ERRO_CRIAR = "Não foi possível criar o profissional";
    public static final String PROFISSIONAL_ERRO_ATUALIZAR = "Não foi possível atualizar o profissional";
    public static final String PROFISSIONAL_ERRO_DELETAR = "Não foi possível deletar o profissional";

    public ProfissionalException(String message) {
        super(message);
    }

}
