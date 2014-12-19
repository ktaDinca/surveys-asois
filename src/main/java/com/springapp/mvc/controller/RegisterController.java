package com.springapp.mvc.controller;

import com.springapp.mvc.model.Role;
import com.springapp.mvc.model.User;
import com.springapp.mvc.service.UserService;
import com.springapp.mvc.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Catalin Dinca (alexandru.dinca2110@gmail.com)
 * @since 18/Dec/2014
 */

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/register", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> registerUser(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();

        String _username = request.getParameter("username");
        String _password = request.getParameter("password");
        String _country = request.getParameter("country");

        User loggedInUser = userService.attemptRegister(_username, _password, _country);
        if (loggedInUser == null) {
            return null;
        }

        map.put("user", loggedInUser);
        return CommonUtils.createSuccessfulResponseMap(map);
    }
}
