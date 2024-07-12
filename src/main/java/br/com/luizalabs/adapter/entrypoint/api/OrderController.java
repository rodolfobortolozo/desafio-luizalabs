package br.com.luizalabs.adapter.entrypoint.api;

import br.com.luizalabs.adapter.entrypoint.api.dto.ClientDTO;
import br.com.luizalabs.adapter.entrypoint.api.dto.FiltersDTO;
import br.com.luizalabs.adapter.persistence.models.ClientData;
import br.com.luizalabs.domain.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderEndPointAdapter orderEndPointAdapter;


    public OrderController(OrderEndPointAdapter orderEndPointAdapter) {
        this.orderEndPointAdapter = orderEndPointAdapter;
    }


    @PostMapping("/v1/list")
    public ResponseEntity<List<ClientDTO>> listar(@RequestBody FiltersDTO filters) {
        return ResponseEntity.ok(orderEndPointAdapter.searchWithParameter(filters));
    }

}
