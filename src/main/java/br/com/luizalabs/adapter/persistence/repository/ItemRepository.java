package br.com.luizalabs.adapter.persistence.repository;

import br.com.luizalabs.adapter.persistence.models.ItemData;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository extends AbstractRepository <ItemData, Long>{
    @Override
    public Class<ItemData> getEntity() {
        return ItemData.class;
    }
}
