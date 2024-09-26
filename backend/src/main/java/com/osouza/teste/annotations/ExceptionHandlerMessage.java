package com.osouza.teste.annotations;

public @interface ExceptionHandlerMessage {

    String message() default "Erros foram encontrados, tente novamente.";

}
