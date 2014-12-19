package com.springapp.mvc.service;

import com.springapp.mvc.dao.AnswerDAO;
import com.springapp.mvc.model.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Catalin Dinca (alexandru.dinca2110@gmail.com)
 * @since 15/Dec/2014
 */
@Service
@Transactional
public class AnswerService {

    @Autowired
    private AnswerDAO answerDAO;

    public List<Answer> createListAnswers(String[] _answers) {
        if (_answers == null || _answers.length == 0) {
            return new ArrayList<Answer>();
        }

        List<Answer> answers = new ArrayList<Answer>();
        Answer temp;
        for (String answer : _answers) {
            temp = new Answer(answer);
            answers.add(temp);
        }
        return answers;
    }

    public Answer findAnswer(Long answerId) {
        return answerDAO.findAnswer(answerId);
    }
}
