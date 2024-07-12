package br.com.luizalabs.application.port.entrypoint.api;

import br.com.luizalabs.adapter.entrypoint.api.dto.ClientDTO;
import br.com.luizalabs.adapter.entrypoint.api.dto.FiltersDTO;

import java.util.List;
import java.util.Map;

public interface OrderEndPointPort {

    List<ClientDTO> getAll();

    List<ClientDTO> searchWithParameter(FiltersDTO filters);
}
