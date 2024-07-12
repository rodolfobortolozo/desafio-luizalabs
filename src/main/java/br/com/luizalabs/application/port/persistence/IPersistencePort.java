package br.com.luizalabs.application.port.persistence;

import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;

public interface IPersistencePort<T,K> {

    List<T> findAll();

}
