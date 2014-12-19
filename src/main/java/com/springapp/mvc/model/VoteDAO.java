package com.springapp.mvc.model;

import com.springapp.mvc.dao.BaseDao;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * @author Catalin Dinca (alexandru.dinca2110@gmail.com)
 * @since 19/Dec/2014
 */
@Repository
public class VoteDAO extends BaseDao {

    public Vote findVote(Long userId, Long answerId) {
        Query q = entityManager.createQuery("Select v from Vote v where v.user.id = :userId and v.answer.id = :answerId");
        q.setParameter("userId", userId).setParameter("answerId", answerId);

        List<Vote> results = q.getResultList();
        return (results != null && results.size() > 0) ? results.get(0) : null;
    }

    public Vote findVote(Long voteId) {
        Query q = entityManager.createQuery("Select v from Vote v where v.id = :id");
        q.setParameter("id", voteId);

        List<Vote> results = q.getResultList();
        return (results != null && results.size() > 0) ? results.get(0) : null;
    }

    public List<Vote> getVotesForAnswer(Answer a) {
        Query q = entityManager.createQuery("Select v from Vote v where v.answer.id = :id").setParameter("id", a.getId());

        return q.getResultList();
    }

    public List<Vote> getVotesForSurvey(Survey survey) {
        Query q = entityManager.createQuery("Select v from Vote v where v.answer.survey.id = :id").setParameter("id", survey.getId());

        return q.getResultList();
    }

    public List<Vote> findAllVotes() {
        Query q = entityManager.createQuery("Select v from Vote v");

        return q.getResultList();
    }

    public List<Vote> findVotesByUser(User user) {
        Query q = entityManager.createQuery("Select v from Vote v where v.user.id = :id").setParameter("id", user.getId());

        return q.getResultList();
    }
}
