package br.com.luizalabs.domain;

import br.com.luizalabs.adapter.persistence.models.IEntidade;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product implements IEntidade<Long> {

    private Long id;

}
