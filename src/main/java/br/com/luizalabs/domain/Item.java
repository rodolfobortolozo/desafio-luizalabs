package br.com.luizalabs.domain;

import br.com.luizalabs.adapter.persistence.models.IEntidade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class Item implements IEntidade<Long> {

    private Long id;
    private Order order;
    private Product product;
    private BigDecimal price;

}
