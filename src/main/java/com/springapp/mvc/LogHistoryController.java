package com.springapp.mvc;

import com.springapp.mvc.model.Interaction;
import com.springapp.mvc.service.InteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping(value = "/logs")
public class LogHistoryController {

    @Autowired
    private InteractionService interactionService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getHistoryLogs() {
        Map<String, Object> map = new HashMap<String, Object>();

        List<Interaction> interactionList = interactionService.getAllInteractions();
        map.put("logs", interactionList);
        return map;
    }

}
