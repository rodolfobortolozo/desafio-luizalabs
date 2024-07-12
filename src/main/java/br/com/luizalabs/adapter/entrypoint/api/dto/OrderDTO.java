package br.com.luizalabs.adapter.entrypoint.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDate;
import java.util.List;

@JsonPropertyOrder({"order_id", "total", "date", "products"})
public record OrderDTO(
        @JsonProperty("order_id")
        Long id,
        String total,
        @JsonProperty("date")
        LocalDate dateOrder,
        @JsonProperty("products")
        List<ItemDTO> items
) {
}
