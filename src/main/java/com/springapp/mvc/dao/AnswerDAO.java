package com.springapp.mvc.dao;

import com.springapp.mvc.model.Answer;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;


/**
 * @author Catalin Dinca (alexandru.dinca2110@gmail.com)
 * @since 19/Dec/2014
 */

@Repository
public class AnswerDAO extends BaseDao {

    public Answer findAnswer(Long id) {
        Query q = entityManager.createQuery("Select a from Answer a where a.id = :id").setParameter("id", id);

        List<Answer> results = q.getResultList();
        return (results != null && results.size() > 0) ? results.get(0) : null;
    }

}
