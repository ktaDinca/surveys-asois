package com.springapp.mvc.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Catalin Dinca (alexandru.dinca2110@gmail.com)
 * @since 14/Dec/2014
 */

@Entity
@Table(name = "surveys")
public class Survey implements BaseObject {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "description")
    private String description;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy = "survey")
    private List<Answer> answers = new ArrayList<Answer>();

    @Column(name = "exp_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expDate;

    @Column(name = "disabled")
    private Boolean disabled = false;

    public Survey() {}

    public Survey(List<Answer> answers) {
        this.answers = answers;
    }

    public Survey(String description, List<Answer> answers) {
        this.description = description;
        this.answers = answers;
    }

    public Survey(String description, List<Answer> answers, Date expDate) {
        this.description = description;
        this.answers = answers;
        this.expDate = expDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }
}
