package br.com.luizalabs.adapter.entrypoint.api.exceptions;

import br.com.luizalabs.infrastructure.exceptions.FileException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(FileException.class)
    public ResponseEntity<ErrorResponse> trataError(Exception ex) {
        HttpStatus code = HttpStatus.BAD_REQUEST;
        ErrorResponse error = new ErrorResponse(ex.getMessage(), code.value(), code.toString(), ex.getClass().getSimpleName(), null);
        return ResponseEntity.status(code).body(error);
    }
}
