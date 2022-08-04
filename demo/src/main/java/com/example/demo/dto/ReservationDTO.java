package com.example.demo.dto;

import com.example.demo.model.Reservation;
import com.example.demo.model.Term;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


public class ReservationDTO {

    private long id;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Term term;

    public ReservationDTO(Reservation r) {
        this(r.getId(), r.getTerm());
    }

    public ReservationDTO(long id, Term term) {
        this.id = id;
        this.term = term;
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
