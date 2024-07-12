package br.com.luizalabs.adapter.entrypoint.api.exceptions;

import java.util.List;

public record ErrorResponse(
        String message,
        int code,
        String status,
        String objectName,
        List<ErrorObject> errors
) {

}