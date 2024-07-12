package br.com.luizalabs.adapter.entrypoint.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record ItemDTO(
        Long product_id,
        @JsonProperty("value")
        String price
) {
}
