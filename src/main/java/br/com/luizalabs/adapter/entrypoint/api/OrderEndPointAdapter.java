package br.com.luizalabs.adapter.entrypoint.api;

import br.com.luizalabs.adapter.entrypoint.api.dto.ClientDTO;
import br.com.luizalabs.adapter.entrypoint.api.dto.FiltersDTO;
import br.com.luizalabs.adapter.entrypoint.api.mappers.ClientDTOMapper;
import br.com.luizalabs.application.port.entrypoint.api.OrderEndPointPort;
import br.com.luizalabs.application.usecases.OrderUserCase;
import br.com.luizalabs.infrastructure.annotations.Adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Adapter
public class OrderEndPointAdapter implements OrderEndPointPort {

    private final OrderUserCase orderUserCase;
    private final ClientDTOMapper mapper;

    public OrderEndPointAdapter(OrderUserCase orderUserCase, ClientDTOMapper mapper) {
        this.orderUserCase = orderUserCase;
        this.mapper = mapper;
    }

    @Override
    public List<ClientDTO> getAll() {
        return orderUserCase.orders().stream()
                .map(mapper::clientToDTO)
                .toList();
    }

    @Override
    public List<ClientDTO> searchWithParameter(FiltersDTO filters) {


        return orderUserCase.searchWithParameter(filters).stream()
                .map(mapper::clientToDTO)
                .toList();
    }
}
