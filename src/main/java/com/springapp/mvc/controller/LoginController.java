package com.springapp.mvc.controller;

import com.springapp.mvc.model.LogEvent;
import com.springapp.mvc.model.User;
import com.springapp.mvc.service.UserService;
import com.springapp.mvc.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Catalin Dinca (alexandru.dinca2110@gmail.com)
 * @since 07/Dec/2014
 */
@Controller
public class LoginController {

    public static final String USER_KEY = "user";
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> attemptLogin(HttpServletRequest request, @RequestParam String username, @RequestParam String password) {
        Map<String, Object> map = new HashMap<String, Object>();

        User loggedInUser = userService.attemptLogin(username, password);
        if (loggedInUser == null) {
            return null;
        }else {
            map.put("user", loggedInUser);
            request.getSession().setAttribute(USER_KEY, loggedInUser);
            userService.createLogInteraction(loggedInUser, LogEvent.LOGIN);
        }
        return map;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> attemptLogout(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();

        User user = (User) request.getSession().getAttribute(USER_KEY);
        if (user == null) {
            return CommonUtils.createFailedResponseMap(map);
        }
        userService.createLogInteraction(user, LogEvent.LOGOUT);
        return CommonUtils.createSuccessfulResponseMap(map);
    }




}
