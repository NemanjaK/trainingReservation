package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Korisnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String ime;
    private String prezime;
    private String password;
    private String email;
    private String brojTelefona;
    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private Uloga uloga;
    @JsonIgnore
    @OneToMany(mappedBy = "korisnik")
    private List<Rezultat> rezultati;

    public Korisnik() {
    }

    public Korisnik(long id, String ime, String prezime, String password, String email, String brojTelefona, Uloga uloga, List<Rezultat> rezultati) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.password = password;
        this.email = email;
        this.brojTelefona = brojTelefona;
        this.uloga = uloga;
        this.rezultati = rezultati;
    }

    public List<Rezultat> getRezultati() {
        return rezultati;
    }

    public void setRezultati(List<Rezultat> rezultati) {
        this.rezultati = rezultati;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public Uloga getUloga() {
        return uloga;
    }

    public void setUloga(Uloga uloga) {
        this.uloga = uloga;
    }
}
