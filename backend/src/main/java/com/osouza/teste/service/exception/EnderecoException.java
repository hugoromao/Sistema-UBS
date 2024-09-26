package com.osouza.teste.service.exception;

public class EnderecoException extends BaseException {

    public static final String ENDERECO_NAO_EXISTE = "Não foi possível encontrar o endereço";
    public static final String ENDERECO_ERRO_CRIAR = "Não foi possível criar o endereço";
    public static final String ENDERECO_ERRO_ATUALIZAR = "Não foi possível atualizar o endereço";
    public static final String ENDERECO_ERRO_DELETAR = "Não foi possível deletar o endereço";

    public EnderecoException(String message) {
        super(message);
    }
    
}
