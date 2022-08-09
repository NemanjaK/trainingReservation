package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Reservation {
    @Id
    @Column(unique=true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private User user;
    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private Term term;

    public Reservation() {
    }

    public Reservation(long id, Term term) {
        this.id = id;
        this.term = term;
    }

    public Reservation(long id, User user, Term term) {
        this.id = id;
        this.user = user;
        this.term = term;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }
}
