package br.com.luizalabs.adapter.persistence.repository;

import br.com.luizalabs.adapter.entrypoint.api.dto.FiltersDTO;
import br.com.luizalabs.adapter.persistence.models.ClientData;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ClientRepository extends AbstractRepository <ClientData, Long>{
    @Override
    public Class<ClientData> getEntity() {
        return ClientData.class;
    }

    public List<ClientData> searchWithParameter(FiltersDTO filters){
        Map<String, Object> params = new HashMap<>();

        StringBuilder jqpl = new StringBuilder("FROM ClientData c JOIN FETCH c.orders o  WHERE 1 = 1 ");


        if(filters.idOrder() != null){
            jqpl.append(" AND o.id = :IDPEDIDO ");
            params.put("IDPEDIDO", filters.idOrder());
        }
        if(filters.dataStart() != null){
            jqpl.append(" AND o.dateOrder >= :DTINICIAL ");
            params.put("DTINICIAL", filters.dataStart());
        }

        if(filters.dataEnd() != null){
            jqpl.append(" AND o.dateOrder <= :DTFINAL ");
            params.put("DTFINAL", filters.dataEnd());
        }

        Query query = createQuery(jqpl.toString(), params);

        return extractResult(query);
    }
}
