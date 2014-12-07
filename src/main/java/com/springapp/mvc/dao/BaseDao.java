package com.springapp.mvc.dao;

import com.springapp.mvc.model.BaseObject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Catalin Dinca (alexandru.dinca2110@gmail.com)
 * @since 07/Dec/2014
 */
public class BaseDao {

    @PersistenceContext
    protected EntityManager entityManager;

    public void saveOrUpdate(BaseObject e) {
        if (e == null) {
           throw new NullPointerException("Trying to persist null object!");
        }
        if (e.getId() != null) {
            entityManager.merge(e);
        }
        else {
            entityManager.persist(e);
        }
    }
}
