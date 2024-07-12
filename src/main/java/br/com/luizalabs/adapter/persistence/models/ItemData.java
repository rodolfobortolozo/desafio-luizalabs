package br.com.luizalabs.adapter.persistence.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "TAB004_ITEM")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemData implements IEntidade<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "ORDER_ID")
    private OrderData order;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PRODUCT_ID")
    private ProductData product;

    private BigDecimal price;

}
