package com.springapp.mvc.controller;

import com.springapp.mvc.model.Vote;
import com.springapp.mvc.service.VoteService;
import com.springapp.mvc.service.exception.InvalidInputException;
import com.springapp.mvc.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Catalin Dinca (alexandru.dinca2110@gmail.com)
 * @since 19/Dec/2014
 */
@Controller
@RequestMapping(value = "/votes")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveVote(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();

        String _userId = request.getParameter("user_id");
        String _answerId = request.getParameter("answer_id");

        Vote vote;

        try {
            vote = voteService.buildAndSaveVote(_userId, _answerId);
            map.put("vote", vote);
            return CommonUtils.createSuccessfulResponseMap(map);
        } catch(InvalidInputException e) {
            map.put("message", "Invalid input!");
        } catch(ValidationException e) {
            map.put("message", e.getMessage());
        }
        return map;
    }

}
