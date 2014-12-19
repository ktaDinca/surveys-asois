package com.springapp.mvc.service;

import com.springapp.mvc.model.*;
import com.springapp.mvc.service.exception.InvalidInputException;
import com.springapp.mvc.util.StatData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.Query;
import javax.xml.bind.ValidationException;
import java.util.*;

/**
 * @author Catalin Dinca (alexandru.dinca2110@gmail.com)
 * @since 19/Dec/2014
 */
@Service
@Transactional
public class VoteService {

    @Autowired
    private VoteDAO voteDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private SurveyService surveyService;

    public void save(Vote vote) {
        if (vote == null) {
            return ;
        }
        voteDAO.saveOrUpdate(vote);
    }


    public Vote buildAndSaveVote(String _userId, String _answerId) throws InvalidInputException, ValidationException  {
        Long userId;
        Long answerId;

        try {
            userId = Long.parseLong(_userId);
            answerId = Long.parseLong(_answerId);
        }
        catch (NumberFormatException e) {
            throw new InvalidInputException("INVALID_INPUT!");
        }

        User user = userService.findUserById(userId);
        if (user == null) {
            throw new InvalidInputException("INVALID_INPUT!");
        }

        Answer answer = answerService.findAnswer(answerId);
        if (answer == null) {
            throw new InvalidInputException("INVALID_INPUT!");
        }

        Vote vote = findVote(userId, answerId);
        if (vote != null) {
            throw new ValidationException("VOTE_ALREADY_PLACED!");
        }
        vote = new Vote(user, answer);
        save(vote);

        return vote;
    }

    private Vote findVote(Long userId, Long answerId) {
        return voteDAO.findVote(userId, answerId);
    }

    public Vote findVote(Long voteId) {
        return voteDAO.findVote(voteId);
    }

    public Integer getTotalVotesForAnswer(Answer a) {
        List<Vote> votes = voteDAO.getVotesForAnswer(a);
        return votes != null ? votes.size() : 0;
    }

    public Integer getTotalVotesForSurvey(Survey survey) {
        List<Vote> votes = voteDAO.getVotesForSurvey(survey);
        return votes != null ? votes.size() : 0;
    }

    public List<StatData> findCountryStatistics() {
        List<Vote> votes = findAllVotes();
        Map<String, Integer> result = new HashMap<String, Integer>();
        List<StatData> stats = new ArrayList<StatData>();

        if (votes == null || votes.size() < 1) {
            return stats;
        }

        Integer totalVotes = votes.size();
        for(Vote v : votes) {
            if (result.get(v.getUser().getCountry()) == null) {
                result.put(v.getUser().getCountry(), 1);
            }
            else {
                result.put(v.getUser().getCountry(), result.get(v.getUser().getCountry()) + 1);
            }
        }

        for (String country : result.keySet()) {
            stats.add(new StatData(country, result.get(country) * 100 / totalVotes + "%"));
        }
        return stats;
    }

    private List<Vote> findAllVotes() {
        return voteDAO.findAllVotes();
    }


    public Set<Survey> findSurveysForUser(User user) {
        List<Vote> votes = voteDAO.findVotesByUser(user);
        Set<Survey> surveysVotedIn = new HashSet<Survey>();

        Survey temp;
        for (Vote v : votes) {
            temp = surveyService.getSurveyStatsByVoteId(v.getId());
            surveysVotedIn.add(temp);
        }

        return surveysVotedIn;
    }
}
