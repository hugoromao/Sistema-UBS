package com.osouza.teste.service.exception;

public class BaseException extends RuntimeException {
    public static final String ERRO_ATUALIZAR_SEM_ID = "Necessário passar o id para atualização";

    public BaseException(String message) {
        super(message);
    }

}
