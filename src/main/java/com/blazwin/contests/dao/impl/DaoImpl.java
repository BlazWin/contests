package com.blazwin.contests.dao.impl;

import com.blazwin.contests.dao.Dao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.*;

@Repository
public abstract class DaoImpl<Type> implements Dao<Type> {

    private final Class<Type> entityClass;

    @PersistenceContext
    private EntityManager entityManager;

    public DaoImpl(Class<Type> entityClass) {
        this.entityClass = entityClass;
    }

    public void save(Type object) {
        getEntityManager().persist(object);
        getEntityManager().flush();
    }

    public void merge(Type object) {
        getEntityManager().merge(object);
    }

    public void delete(Type object) {
        getEntityManager().remove(object);
    }

    public List<Type> getAll() {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Type> query = builder.createQuery(entityClass);
        Root<Type> root = query.from(entityClass);
        query.select(root);
        return getEntityManager().createQuery(query).getResultList();
    }

    public Type getById(long id) {
        return getEntityManager().find(entityClass, id);
    }

    public long getCount() {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        query.select(builder.count(query.from(entityClass)));
        return getEntityManager().createQuery(query).getSingleResult();
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

}