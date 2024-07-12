package br.com.luizalabs.adapter.persistence.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TAB005_PRODUCT")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductData implements IEntidade<Long> {

    @Id
    private Long id;

}
