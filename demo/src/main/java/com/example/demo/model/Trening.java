package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Trening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String opis;
    @JsonIgnore
    @OneToMany(mappedBy = "trening")
    private List<Rezultat> rezultati;
    @JsonIgnore
    @OneToMany(mappedBy = "trening")
    private List<Termin> termini;

    public Trening() {
    }

    public Trening(Long id, String opis, List<Rezultat> rezultati, List<Termin> termini) {
        this.id = id;
        this.opis = opis;
        this.rezultati = rezultati;
        this.termini = termini;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public List<Rezultat> getRezultati() {
        return rezultati;
    }

    public void setRezultati(List<Rezultat> rezultati) {
        this.rezultati = rezultati;
    }

    public List<Termin> getTermini() {
        return termini;
    }

    public void setTermini(List<Termin> termini) {
        this.termini = termini;
    }
}
