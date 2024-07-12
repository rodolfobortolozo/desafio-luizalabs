package br.com.luizalabs.adapter.persistence.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "TAB003_ORDER")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderData implements IEntidade<Long> {

    @Id
    private Long id;

    private LocalDate dateOrder;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private ClientData client;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<ItemData> items;

//    @Transient
//    @Setter(AccessLevel.NONE)
//    private BigDecimal total;
//
//    public BigDecimal getTotal(){
//        return items.stream()
//                .map(ItemData::getPrice)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//    }


}
