package com.osouza.teste.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExceptionHandlerResponse {

    public final static String DEFAULT_MESSAGE = "Erros foram encontrados";

    private String message;
    private String exception;
    private Map<String, String> erros;

}
