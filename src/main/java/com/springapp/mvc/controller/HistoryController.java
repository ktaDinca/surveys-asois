package com.springapp.mvc.controller;

import com.springapp.mvc.model.Survey;
import com.springapp.mvc.model.User;
import com.springapp.mvc.service.VoteService;
import com.springapp.mvc.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Catalin Dinca (alexandru.dinca2110@gmail.com)
 * @since 19/Dec/2014
 */
@Controller
@RequestMapping(value = "/history")
public class HistoryController {

    @Autowired
    private SurveyController surveyController;

    @Autowired
    private VoteService voteService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getHistory(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return CommonUtils.createFailedResponseMap(map);
        }

        Set<Survey> surveys = voteService.findSurveysForUser(user);
        map.put("historySurveys", surveys);

        return CommonUtils.createSuccessfulResponseMap(map);
    }

}
