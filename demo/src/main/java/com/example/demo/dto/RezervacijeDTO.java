package com.example.demo.dto;

import com.example.demo.model.Rezervacija;
import com.example.demo.model.Termin;


public class RezervacijeDTO {

    private long id;
    private Termin termin;

    public RezervacijeDTO(Rezervacija r) {
        this(r.getId(), r.getTermin());
    }

    public RezervacijeDTO(long id, Termin termin) {
        this.id = id;
        this.termin = termin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Termin getTermin() {
        return termin;
    }

    public void setTermin(Termin termin) {
        this.termin = termin;
    }
}
