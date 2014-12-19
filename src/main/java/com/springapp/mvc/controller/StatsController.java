package com.springapp.mvc.controller;

import com.springapp.mvc.model.Survey;
import com.springapp.mvc.service.SurveyService;
import com.springapp.mvc.service.VoteService;
import com.springapp.mvc.util.CommonUtils;
import com.springapp.mvc.util.StatData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Catalin Dinca (alexandru.dinca2110@gmail.com)
 * @since 19/Dec/2014
 */
@Controller
@RequestMapping(value = "/stats")
public class StatsController {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private VoteService voteService;

    @RequestMapping(value = "/{id}")
    @ResponseBody
    public Map<String, Object> getSurveyStats(@PathVariable("id") Long id) {
        Map<String, Object> map = new HashMap<String, Object>();

        Survey survey = surveyService.getSurveyStatsByVoteId(id);
        map.put("stats", survey);
        return map;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getStatsData(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();

        List<StatData> statDatas = voteService.findCountryStatistics();
        if(statDatas == null || statDatas.size() < 1) {
            return CommonUtils.createFailedResponseMap(map);
        }

        map.put("stats", statDatas);
        return CommonUtils.createSuccessfulResponseMap(map);
    }
}
