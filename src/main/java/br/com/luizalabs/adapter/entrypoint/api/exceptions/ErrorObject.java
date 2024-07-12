package br.com.luizalabs.adapter.entrypoint.api.exceptions;

public record ErrorObject(
        String field,
        String message,
        Object parameter) {

}
