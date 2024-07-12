package br.com.luizalabs.adapter.persistence.repository;

import br.com.luizalabs.adapter.persistence.models.ProductData;
import org.springframework.stereotype.Component;

@Component
public class ProductRepository extends AbstractRepository <ProductData, Long>{
    @Override
    public Class<ProductData> getEntity() {
        return ProductData.class;
    }
}
