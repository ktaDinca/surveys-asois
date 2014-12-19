package com.springapp.mvc.service;

import com.springapp.mvc.dao.UserDao;
import com.springapp.mvc.model.LogEvent;
import com.springapp.mvc.model.Role;
import com.springapp.mvc.model.User;
import com.springapp.mvc.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


/**
 * @author Catalin Dinca (alexandru.dinca2110@gmail.com)
 * @since 07/Dec/2014
 */

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private InteractionService interactionService;

    public User attemptLogin(String username, String password) {
        if (username == null || password == null) {
            return null;
        }
        return userDao.findUser(username, password);
    }


    public User attemptRegister(String username, String password, String country) {
        if (!validateUserCredentials(username, password, country)) {
            return null;
        }

        User user = buildUser(username, password, country, Role.USER);
        saveOrUpdate(user);
        return user;
    }

    private User buildUser(String username, String password, String country, Role role) {
        return new User(username, password, country.toUpperCase(), role);
    }

    private boolean validateUserCredentials(String username, String password, String country) {
        if (CommonUtils.isStringEmptyOrNull(username) ||
                CommonUtils.isStringEmptyOrNull(password) ||
                CommonUtils.isStringEmptyOrNull(country)) {
            return false;
        }
        return true;
    }

    public void saveOrUpdate(User user) {
        userDao.saveOrUpdate(user);
    }

    public void createLogInteraction(User loggedInUser, LogEvent logEvent) {
        if (loggedInUser == null) {
            return;
        }

        if (Role.ADMIN.equals(loggedInUser.getRole()) || Role.ANALYST.equals(loggedInUser.getRole())) {
            interactionService.createInteraction(loggedInUser, logEvent, new Date());
        }
    }
}
