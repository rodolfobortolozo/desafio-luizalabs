package br.com.luizalabs.adapter.persistence;

import br.com.luizalabs.adapter.entrypoint.api.dto.FiltersDTO;
import br.com.luizalabs.adapter.entrypoint.api.mappers.ClientDataMapper;
import br.com.luizalabs.adapter.persistence.repository.ClientRepository;
import br.com.luizalabs.application.port.persistence.ClientPort;
import br.com.luizalabs.domain.Client;
import br.com.luizalabs.infrastructure.annotations.Adapter;

import java.util.List;
import java.util.Map;

@Adapter
public class ClientAdapter implements ClientPort<Client , Long> {

    private final ClientRepository repository;

    private final ClientDataMapper mapper;

    public ClientAdapter(ClientRepository repository, ClientDataMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public List<Client> findAll() {
        return repository.findAll().stream()
                .map(mapper::dataToClient)
                .toList();
    }

    @Override
    public List<Client> searchWithParameter(FiltersDTO filters) {
        return repository.searchWithParameter(filters).stream()
                .map(mapper::dataToClient)
                .toList();
    }
}
