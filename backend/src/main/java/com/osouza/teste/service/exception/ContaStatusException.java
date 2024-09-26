package com.osouza.teste.service.exception;

public class ContaStatusException extends BaseException {

    public static final String CONTA_STATUS_NAO_EXISTE = "Não foi possível encontrar o status da conta";
    public static final String CONTA_STATUS_ERRO_CRIAR = "Não foi possível criar o status da conta";
    public static final String CONTA_STATUS_ERRO_ATUALIZAR = "Não foi possível atualizar o status da conta";
    public static final String CONTA_STATUS_ERRO_DELETAR = "Não foi possível deletar o status da conta";

    public ContaStatusException(String message) {
        super(message);
    }

}
