package br.com.luizalabs.adapter.entrypoint.api.dto;

import java.time.LocalDate;

public record FiltersDTO(
        Long idOrder,
        LocalDate dataStart,
        LocalDate dataEnd
) {

}
