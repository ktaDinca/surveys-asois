package com.springapp.mvc.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;

/**
 * @author Catalin Dinca (alexandru.dinca2110@gmail.com)
 * @since 07/Dec/2014
 */
@Entity
@Table(name = "USERS")
public class User implements BaseObject {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "country")
    private String country;

    public User() {}

    public User(String username, String password, String country, Role role) {
        this.username = username;
        this.password = password;
        this.country = country;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

