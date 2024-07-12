package br.com.luizalabs.adapter.persistence.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "TAB002_CLIENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientData implements IEntidade<Long> {

    @Id
    private Long id;
    private String name;

    @OneToMany(mappedBy = "client")
    private List<OrderData> orders;
}
