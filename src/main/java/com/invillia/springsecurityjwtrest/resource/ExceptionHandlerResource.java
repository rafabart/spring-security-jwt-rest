package com.invillia.springsecurityjwtrest.resource;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//Define que a classe abaixo será um manipulador personalizado de exceptions.
@ControllerAdvice
public class ExceptionHandlerResource {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        final Map<String, List<String>> result = e.getBindingResult().getAllErrors()
                .stream()
                .map(it -> (FieldError) it)
                .collect(
                        Collectors.groupingBy(
                                FieldError::getField,
                                Collectors.mapping(
                                        DefaultMessageSourceResolvable::getDefaultMessage,
                                        Collectors.toList()
                                )
                        )
                );
        return ResponseEntity.badRequest().body(result);
    }

}
