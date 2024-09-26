package com.osouza.teste.service.exception;

public class UsuarioException extends BaseException {

    public static final String USUARIO_NAO_EXISTE = "Não foi possível encontrar o usuario";
    public static final String USUARIO_ERRO_CRIAR = "Não foi possível criar o usuario";
    public static final String USUARIO_ERRO_ATUALIZAR = "Não foi possível atualizar o usuario";
    public static final String USUARIO_ERRO_ATUALIZAR_SENHA = "Não foi possível atualizar a senha";
    public static final String USUARIO_ERRO_ATUALIZAR_SEM_ID = "Necessário passar o id para atualização";
    public static final String USUARIO_ERRO_DELETAR = "Não foi possível deletar o usuario";

    public UsuarioException(String message) {
        super(message);
    }

}
