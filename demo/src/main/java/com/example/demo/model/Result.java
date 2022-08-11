package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String time;
    private String numbersOfrounds;
    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private User user;

    public Result() {
    }

    public Result(Long id, String time, String numbersOfrounds, User user) {
        this.id = id;
        this.time = time;
        this.numbersOfrounds = numbersOfrounds;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNumbersOfrounds() {
        return numbersOfrounds;
    }

    public void setNumbersOfrounds(String numbersOfrounds) {
        this.numbersOfrounds = numbersOfrounds;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
