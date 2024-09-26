package com.osouza.teste.service.exception;

public class ClienteException extends BaseException {

    public static final String CLIENTE_NAO_EXISTE = "Não foi possível encontrar o cliente";
    public static final String CLIENTE_ERRO_CRIAR = "Não foi possível criar o cliente";
    public static final String CLIENTE_ERRO_ATUALIZAR = "Não foi possível atualizar o cliente";
    public static final String CLIENTE_ERRO_DELETAR = "Não foi possível deletar o cliente";

    public ClienteException(String message) {
        super(message);
    }

}
