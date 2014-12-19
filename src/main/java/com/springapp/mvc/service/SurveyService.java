package com.springapp.mvc.service;

import com.springapp.mvc.dao.SurveyDao;
import com.springapp.mvc.model.Answer;
import com.springapp.mvc.model.Survey;
import com.springapp.mvc.model.Vote;
import com.springapp.mvc.service.exception.InvalidInputException;
import com.springapp.mvc.util.CommonUtils;
import org.omg.CORBA.DynAnyPackage.Invalid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author Catalin Dinca (alexandru.dinca2110@gmail.com)
 * @since 15/Dec/2014
 */
@Service
@Transactional
public class SurveyService {

    @Autowired
    private SurveyDao surveyDao;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private VoteService voteService;


    public Survey buildSurvey(String _description, String[] _answers, String _expDate) {

        if (CommonUtils.isStringEmptyOrNull(_description) || CommonUtils.isStringEmptyOrNull(_expDate) ||
                _answers == null || _answers.length == 0) {
            throw new InvalidInputException("invalid_input");
        }

        Date expdDate = new Date(Long.parseLong(_expDate));
        List<Answer> answerList = answerService.createListAnswers(_answers);

        return new Survey(_description, answerList, expdDate);
    }

    public boolean buildAndSaveSurvey(String _description, String[] _answers, String _expDate) {
        Survey survey;

        try {
            survey = buildSurvey(_description, _answers, _expDate);
            hookAnswersToSurvey(survey);
            saveOrUpdate(survey);
            return true;
        } catch (InvalidInputException e) {
            return false;
        }
    }

    private void hookAnswersToSurvey(Survey s) {
        if (s == null) {
            return;
        }
        for (Answer a : s.getAnswers()) {
            a.setSurvey(s);
        }
    }

    private void saveOrUpdate(Survey survey) {
        surveyDao.saveOrUpdate(survey);
    }

    public List<Survey> getActiveSurveys() {
        return surveyDao.findActiveSurveys();
    }


    public List<Survey> getExpiredSurveys() {
        return surveyDao.findExpiredSurveys();
    }

    public void deleteSurvey(Long id) {
        if (id == null) {
            return;
        }
        surveyDao.deleteSurvey(id);
    }

    public void disableSurvey(Long id) {
        if (id == null) {
            return;
        }
        surveyDao.disableSurvey(id);
    }

    public void enableSurvey(Long id) {
        if (id == null) {
            return;
        }
        surveyDao.enableSurvey(id);
    }

    public Survey getSurveyStatsByVoteId(Long voteId) {
        Vote vote = voteService.findVote(voteId);
        if (vote == null) {
            throw new InvalidInputException("Cannot find placed vote!");
        }

        Survey response = new Survey();
        Survey survey = vote.getAnswer().getSurvey();
        Integer surveyTotalVotes = voteService.getTotalVotesForSurvey(survey);

        response.setId(survey.getId());
        response.setDescription(survey.getDescription());
        response.setDisabled(survey.getDisabled());
        response.setExpDate(survey.getExpDate());

        for (Answer a : survey.getAnswers()) {
            Integer _result = voteService.getTotalVotesForAnswer(a);
            if (_result > 0) {
                response.getAnswers().add(new Answer(a.getText() + "  " + _result * 100 / surveyTotalVotes + "%"));
            }
            else {
                response.getAnswers().add(new Answer(a.getText() + "  " + "0%"));
            }
        }
        return response;
    }
}
