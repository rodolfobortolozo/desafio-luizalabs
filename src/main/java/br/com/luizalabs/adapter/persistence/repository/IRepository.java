package br.com.luizalabs.adapter.persistence.repository;

import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;

public interface IRepository<T,K> {

    T persist(T entity);
    T update(T entity);
    boolean delete(T entity);
    T save(T entity);
    Optional<T> findById(K id);
    List<T> findAll();
    Query createQuery(String jpql);
}
