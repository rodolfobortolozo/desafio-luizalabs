package br.com.luizalabs.adapter.entrypoint.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @param <T> Class Request
 * @param <R> Class Response
 * @param <K> Type ID
 */
public interface AbstractRestController<T, R, K extends Serializable> {

    @PostMapping
    public ResponseEntity<R> create(@RequestBody T r);

    @GetMapping
    public ResponseEntity<List<R>> getAll();

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") K id);

    @GetMapping("/{id}")
    public ResponseEntity<R> findById(@PathVariable("id") K id);

}
