package com.springapp.mvc.controller;

import com.springapp.mvc.model.Survey;
import com.springapp.mvc.service.SurveyService;
import com.springapp.mvc.service.exception.InvalidInputException;
import com.springapp.mvc.util.CommonUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ser.impl.JsonSerializerMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * @author Catalin Dinca (alexandru.dinca2110@gmail.com)
 * @since 14/Dec/2014
 */
@Controller
@RequestMapping("/")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveSurvey(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();

        String _description = request.getParameter("description");
        String[] _answers = request.getParameterMap().get("answers");
        String _expDate = request.getParameter("expDate");

        if (surveyService.buildAndSaveSurvey(_description, _answers, _expDate)) {
            return CommonUtils.createSuccessfulResponseMap(map);
        }
        return CommonUtils.createFailedResponseMap(map, Arrays.asList("invalid_input"));
    }

    @RequestMapping("/active")
    @ResponseBody
    public Map<String, Object> getActive(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();

        List<Survey> surveys = surveyService.getActiveSurveys();
        if (surveys == null) {
            return CommonUtils.createFailedResponseMap(map);
        }
        map.put("surveys", surveys);
        return CommonUtils.createSuccessfulResponseMap(map);
    }

    @RequestMapping("/expired")
    @ResponseBody
    public Map<String, Object> getExpired(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();

        List<Survey> surveys = surveyService.getExpiredSurveys();
        if (surveys == null) {
            return CommonUtils.createFailedResponseMap(map);
        }
        map.put("surveys", surveys);
        return CommonUtils.createSuccessfulResponseMap(map);
    }

    @RequestMapping("/{id}/delete")
    @ResponseBody
    public Map<String, Object> deleteSurvey(@PathVariable("id") Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (id == null) {
            return CommonUtils.createFailedResponseMap(map);
        }

        surveyService.deleteSurvey(id);
        return CommonUtils.createSuccessfulResponseMap(map);
    }

    @RequestMapping("/{id}/disable")
    @ResponseBody
    public Map<String, Object> disableSurvey(@PathVariable("id") Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (id == null) {
            return CommonUtils.createFailedResponseMap(map);
        }

        surveyService.disableSurvey(id);
        return CommonUtils.createSuccessfulResponseMap(map);
    }

    @RequestMapping("/{id}/enable")
    @ResponseBody
    public Map<String, Object> enableSurvey(@PathVariable("id") Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (id == null) {
            return CommonUtils.createFailedResponseMap(map);
        }

        surveyService.enableSurvey(id);
        return CommonUtils.createSuccessfulResponseMap(map);
    }


}
