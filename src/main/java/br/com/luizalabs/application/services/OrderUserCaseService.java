package br.com.luizalabs.application.services;

import br.com.luizalabs.adapter.entrypoint.api.dto.FiltersDTO;
import br.com.luizalabs.adapter.entrypoint.api.mappers.ClientDataMapper;
import br.com.luizalabs.application.port.persistence.ClientPort;
import br.com.luizalabs.application.usecases.OrderUserCase;
import br.com.luizalabs.domain.Client;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderUserCaseService implements OrderUserCase {

    private final ClientPort persistence;
    private final ClientDataMapper mapper;

    public OrderUserCaseService(ClientPort persistence, ClientDataMapper mapper) {
        this.persistence = persistence;
        this.mapper = mapper;
    }


    @Override
    public List<Client> orders() {
        return persistence.findAll();
    }

    @Override
    public List<Client> searchWithParameter(FiltersDTO filters) {
        return persistence.searchWithParameter(filters);
    }
}
