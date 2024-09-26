package com.osouza.teste.service.exception;

public class CronogramaException extends BaseException {
    public static final String CRONOGRAMA_NAO_EXISTE = "Não foi possível encontrar a cronograma";
    public static final String CRONOGRAMA_ERRO_CRIAR = "Não foi possível criar a cronograma";
    public static final String CRONOGRAMA_ERRO_ATUALIZAR = "Não foi possível atualizar a cronograma";
    public static final String CRONOGRAMA_ERRO_DELETAR = "Não foi possível deletar a cronograma";

    public CronogramaException(String message) {
        super(message);
    }

}
