package br.com.luizalabs.application.usecases;

import br.com.luizalabs.adapter.entrypoint.api.dto.FiltersDTO;
import br.com.luizalabs.adapter.persistence.models.ClientData;
import br.com.luizalabs.domain.Client;

import java.util.List;
import java.util.Map;

public interface OrderUserCase {

    List<Client> orders();

    List<Client> searchWithParameter(FiltersDTO filters);
}
