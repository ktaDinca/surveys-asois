package com.springapp.mvc.service;

import com.springapp.mvc.dao.UserDao;
import com.springapp.mvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Catalin Dinca (alexandru.dinca2110@gmail.com)
 * @since 07/Dec/2014
 */

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User attemptLogin(String username, String password) {
        if (username == null || password == null) {
            return null;
        }
        return userDao.findUser(username, password);
    }


}
