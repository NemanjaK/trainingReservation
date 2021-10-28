package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Rezultat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vreme;
    private String brojRundi;
    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private Korisnik korisnik;
    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private Trening trening;

    public Rezultat() {
    }

    public Rezultat(Long id, String vreme, String brojRundi, Korisnik korisnik, Trening trening) {
        this.id = id;
        this.vreme = vreme;
        this.brojRundi = brojRundi;
        this.korisnik = korisnik;
        this.trening = trening;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVreme() {
        return vreme;
    }

    public void setVreme(String vreme) {
        this.vreme = vreme;
    }

    public String getBrojRundi() {
        return brojRundi;
    }

    public void setBrojRundi(String brojRundi) {
        this.brojRundi = brojRundi;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Trening getTrening() {
        return trening;
    }

    public void setTrening(Trening trening) {
        this.trening = trening;
    }
}
