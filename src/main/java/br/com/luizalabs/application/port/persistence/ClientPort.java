package br.com.luizalabs.application.port.persistence;

import br.com.luizalabs.adapter.entrypoint.api.dto.FiltersDTO;
import br.com.luizalabs.domain.Client;

import java.util.List;
import java.util.Map;

public interface ClientPort<T,K> extends IPersistencePort<T,K> {

    List<Client> searchWithParameter(FiltersDTO filters);
}
