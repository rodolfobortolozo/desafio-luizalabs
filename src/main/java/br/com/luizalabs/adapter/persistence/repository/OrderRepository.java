package br.com.luizalabs.adapter.persistence.repository;

import br.com.luizalabs.adapter.persistence.models.OrderData;
import org.springframework.stereotype.Component;

@Component
public class OrderRepository extends AbstractRepository <OrderData, Long>{
    @Override
    public Class<OrderData> getEntity() {

        return OrderData.class;
    }
}
