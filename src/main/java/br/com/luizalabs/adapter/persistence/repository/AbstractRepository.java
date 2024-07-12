package br.com.luizalabs.adapter.persistence.repository;

import br.com.luizalabs.adapter.persistence.models.IEntidade;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Transactional
public abstract class AbstractRepository<T extends IEntidade<K>, K extends Serializable> implements IRepository<T,K> {

    Logger logger = LoggerFactory.getLogger(AbstractRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    public abstract Class<T> getEntity();

    public T persist(T entity) {
        return entityManager.merge(entity);
    }

    public T update(T entity) {
        return entityManager.merge(entity);
    }

    public T save(T entity) {
        if (entity.getId() == null) {
            return persist(entity);
        }
        return update(entity);
    }

    public boolean delete(T entity) {
        try {
            entityManager.remove(entity);
            return true;
        } catch (PersistenceException e) {
            return false;
        }
    }

    public Optional<T> findById(K id) {
        return Optional.ofNullable(entityManager.find(getEntity(), id));
    }

    public List<T> findAll() {
        Query query = createQuery("FROM " + getEntity().getSimpleName());
        return extractResult(query);
    }

    public Query createQuery(String jpql) {
        return entityManager.createQuery(jpql);

    }

    public Query createQuery(String jpql, Map<String, Object> parameters) {
        Query query = entityManager.createQuery(jpql);

        return addParameter(parameters, query);
    }

    protected List<T> extractResult(Query query){
        return query.getResultList();
    }

    protected Query addParameter(Map<String, Object> parameters, Query query) {
        Set<Map.Entry<String, Object>> entrySet = parameters.entrySet();

        for (Map.Entry<String, Object> param : entrySet) {
            query.setParameter(param.getKey(), param.getValue());
            logger.info("Add parameter " + param.getKey() + " : " + param.getValue());
        }
        return query;
    }
}
