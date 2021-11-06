package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
public class Trening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String opis;
    private String tipTreninga;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate datumTreninga;
    @JsonIgnore
    @OneToMany(mappedBy = "trening")
    private List<Rezultat> rezultati;
    @JsonIgnore
    @OneToMany(mappedBy = "trening")
    private List<Termin> termini;

    public Trening() {
    }

    public Trening(Long id, String opis, String tipTreninga, LocalDate datumTreninga, List<Rezultat> rezultati, List<Termin> termini) {
        this.id = id;
        this.opis = opis;
        this.tipTreninga = tipTreninga;
        this.datumTreninga = datumTreninga;
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

    public String getTipTreninga() {
        return tipTreninga;
    }

    public void setTipTreninga(String tipTreninga) {
        this.tipTreninga = tipTreninga;
    }

    public LocalDate getDatumTreninga() {
        return datumTreninga;
    }

    public void setDatumTreninga(LocalDate datumTreninga) {
        this.datumTreninga = datumTreninga;
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
