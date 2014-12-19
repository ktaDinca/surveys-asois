package com.springapp.mvc.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Catalin Dinca (alexandru.dinca2110@gmail.com)
 * @since 18/Dec/2014
 */
@Entity
@Table(name = "logs")
public class Interaction implements BaseObject {

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time")
    private Date time;

    @Column(name = "event")
    @Enumerated(EnumType.STRING)
    private LogEvent logEvent;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Interaction(Date time, LogEvent logEvent, User user) {
        this.time = time;
        this.logEvent = logEvent;
        this.user = user;
    }

    public Interaction() {
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public LogEvent getLogEvent() {
        return logEvent;
    }

    public void setLogEvent(LogEvent logEvent) {
        this.logEvent = logEvent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
