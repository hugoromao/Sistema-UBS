package com.osouza.teste.service.exception;

public class ConsultaException extends BaseException {

    public static final String CONSULTA_NAO_EXISTE = "Não foi possível encontrar a consulta";
    public static final String CONSULTA_ERRO_CRIAR = "Não foi possível criar a consulta";
    public static final String CONSULTA_ERRO_ATUALIZAR = "Não foi possível atualizar a consulta";
    public static final String CONSULTA_ERRO_DELETAR = "Não foi possível deletar a consulta";

    public ConsultaException(String message) {
        super(message);
    }

}
