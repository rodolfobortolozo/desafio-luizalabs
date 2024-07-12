package br.com.luizalabs.domain;

import br.com.luizalabs.adapter.persistence.models.IEntidade;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Client implements IEntidade<Long> {


    private Long id;
    private String name;
    private List<Order> orders;
}
