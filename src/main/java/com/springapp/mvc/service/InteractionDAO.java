package com.springapp.mvc.service;

import com.springapp.mvc.dao.BaseDao;
import com.springapp.mvc.model.Interaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * @author Catalin Dinca (alexandru.dinca2110@gmail.com)
 * @since 18/Dec/2014
 */
@Repository
public class InteractionDAO extends BaseDao {

    public List<Interaction> findAllInteractions() {
        Query q = entityManager.createQuery("Select i from Interaction i order by i.id desc");
        return q.getResultList();
    }
}
