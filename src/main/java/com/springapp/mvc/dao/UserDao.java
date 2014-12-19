package com.springapp.mvc.dao;

import com.springapp.mvc.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * @author Catalin Dinca (alexandru.dinca2110@gmail.com)
 * @since 07/Dec/2014
 */
@Repository
public class UserDao extends BaseDao {


    public User findUser(String username, String password) {
        Query q = entityManager.createQuery("Select u from User u where u.username = :username and u.password = :password");
        q.setParameter("username", username);
        q.setParameter("password", password);

        List<User> results = q.getResultList();

        return (results != null && results.size() > 0) ? results.get(0) : null;
    }

    public User findUser(Long id) {
        Query q = entityManager.createQuery("Select u from User u where u.id = :id").setParameter("id", id);
        List<User> results = q.getResultList();

        return (results != null && results.size() > 0) ? results.get(0) : null;
    }
}
