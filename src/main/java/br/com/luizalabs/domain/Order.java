package br.com.luizalabs.domain;

import br.com.luizalabs.adapter.persistence.models.IEntidade;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class Order implements IEntidade<Long> {

    private Long id;
    private LocalDate dateOrder;
    private Client client;
    private List<Item> items;

    @Setter(AccessLevel.NONE)
    private BigDecimal total;

    public BigDecimal getTotal(){
        return items.stream()
                .map(Item::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
