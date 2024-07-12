package br.com.luizalabs.adapter.entrypoint.api.mappers;

import br.com.luizalabs.adapter.entrypoint.api.dto.ClientDTO;
import br.com.luizalabs.adapter.entrypoint.api.dto.ItemDTO;
import br.com.luizalabs.adapter.entrypoint.api.dto.OrderDTO;
import br.com.luizalabs.adapter.persistence.models.ClientData;
import br.com.luizalabs.domain.Client;
import br.com.luizalabs.domain.Item;
import br.com.luizalabs.domain.Order;
import br.com.luizalabs.domain.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;


@Mapper(componentModel = "spring")
public interface ClientDTOMapper {

    ClientDTOMapper INSTANCE = Mappers.getMapper(ClientDTOMapper.class);

    ClientDTO clientToDTO(Client clientData);

    @Mappings({
            @Mapping(target = "product_id", expression = "java(idProduct(item.getProduct()))"),
            @Mapping(target = "price", expression = "java(valuetoString(item.getPrice()))")
    })
    ItemDTO toItemDTO(Item item);

    @Mappings({
            @Mapping(target = "total", expression = "java(valuetoString(order.getTotal()))")
    })
    OrderDTO toOrderDTO(Order order);

    default Long idProduct(Product product){
        return product.getId();
    }

    default String valuetoString(BigDecimal value){
        return value.toString();
    }


}
