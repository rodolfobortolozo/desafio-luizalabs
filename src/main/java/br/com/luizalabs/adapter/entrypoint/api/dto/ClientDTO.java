package br.com.luizalabs.adapter.entrypoint.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"user_id","name","orders"})
public record ClientDTO (

        @JsonProperty("user_id")
        Long id,
        String name,
        List<OrderDTO> orders
){


}
