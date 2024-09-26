package com.osouza.teste.utils;

import com.osouza.teste.annotations.ExceptionHandlerMessage;
import com.osouza.teste.domain.model.ExceptionHandlerResponse;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.HandlerMethod;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class SecurityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionHandlerResponse> handlerException(RuntimeException exception, HandlerMethod handler) {
        ExceptionHandlerMessage message = AnnotationUtils.findAnnotation(handler.getMethod(), ExceptionHandlerMessage.class);
        ExceptionHandlerResponse exceptionHandlerResponse = new ExceptionHandlerResponse();
        exceptionHandlerResponse.setException(exception.getMessage());

        if (message != null) {
             exceptionHandlerResponse.setMessage(message.message());
        } else {
            exceptionHandlerResponse.setMessage(ExceptionHandlerResponse.DEFAULT_MESSAGE);
        }

        return ResponseEntity.badRequest().body(exceptionHandlerResponse);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ExceptionHandlerResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        Map<String, String> errors = new HashMap<>();
        ExceptionHandlerResponse errorsResponse = new ExceptionHandlerResponse();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        errorsResponse.setMessage("Erros foram encontrados nos objetos de requisição");
        errorsResponse.setErros(errors);
        return ResponseEntity.badRequest().body(errorsResponse);

    }

}
