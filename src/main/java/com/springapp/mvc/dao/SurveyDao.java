package com.springapp.mvc.dao;

import com.springapp.mvc.model.BaseObject;
import com.springapp.mvc.model.Survey;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * @author Catalin Dinca (alexandru.dinca2110@gmail.com)
 * @since 15/Dec/2014
 */
@Repository
public class SurveyDao extends BaseDao {


    public List<Survey> findActiveSurveys() {
        Query q = entityManager.createQuery("Select s from Survey s where s.expDate > :now");
        q.setParameter("now", new Date());

        return q.getResultList();
    }

    public List<Survey> findExpiredSurveys() {
        Query q = entityManager.createQuery("Select s from Survey s where s.expDate < :now");
        q.setParameter("now", new Date());

        return q.getResultList();
    }

    public void deleteSurvey(Long id) {

        Query q1 = entityManager.createQuery("Delete Answer a where a.survey.id = :id");
        q1.setParameter("id", id);
        q1.executeUpdate();

        Query q2 = entityManager.createQuery("Delete Survey s where s.id = :id");
        q2.setParameter("id", id);

        q2.executeUpdate();
    }

    public void disableSurvey(Long id) {
        Query q = entityManager.createQuery("Update Survey s set s.disabled = true where s.id = :id");
        q.setParameter("id", id);

        q.executeUpdate();
    }

    public void enableSurvey(Long id) {
        Query q = entityManager.createQuery("Update Survey s set s.disabled = false where s.id = :id");
        q.setParameter("id", id);

        q.executeUpdate();
    }
}
