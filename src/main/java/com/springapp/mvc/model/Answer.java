package com.springapp.mvc.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;

/**
 * @author Catalin Dinca (alexandru.dinca2110@gmail.com)
 * @since 14/Dec/2014
 */
@Entity
@Table(name = "answers")
public class Answer implements BaseObject {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "text")
    private String text;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "survey_id", nullable = false)
    private Survey survey;

    @JsonIgnore
    @Column(name = "votes")
    private Integer votes = 0;

    public Answer() {}

    public Answer(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
