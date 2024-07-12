package br.com.luizalabs.adapter.entrypoint.api.mappers;

import br.com.luizalabs.adapter.persistence.models.ClientData;
import br.com.luizalabs.adapter.persistence.models.ItemData;
import br.com.luizalabs.adapter.persistence.models.OrderData;
import br.com.luizalabs.domain.Client;
import br.com.luizalabs.domain.Item;
import br.com.luizalabs.domain.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface ClientDataMapper {

    ClientDataMapper INSTANCE = Mappers.getMapper(ClientDataMapper.class);


    Client dataToClient(ClientData clientData);

    @Mapping(target = "client", ignore = true)
    Order dataOrderToDTO(OrderData orderData);

    @Mapping(target = "order", ignore = true)
    Item dataItemToItem(ItemData itemData);


}
