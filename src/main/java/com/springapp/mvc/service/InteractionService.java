package com.springapp.mvc.service;

import com.springapp.mvc.model.Interaction;
import com.springapp.mvc.model.LogEvent;
import com.springapp.mvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author Catalin Dinca (alexandru.dinca2110@gmail.com)
 * @since 18/Dec/2014
 */
@Service
@Transactional
public class InteractionService {

    @Autowired
    private InteractionDAO interactionDao;

    public void save(Interaction interaction) {
        if (interaction == null) {
            return;
        }
        interactionDao.saveOrUpdate(interaction);
    }

    public void createInteraction(User loggedInUser, LogEvent login, Date date) {
        if (loggedInUser == null || date == null) {
            return;
        }
        save(new Interaction(date, login, loggedInUser));
    }

    public List<Interaction> getAllInteractions() {
        return interactionDao.findAllInteractions();
    }
}
