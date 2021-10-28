package com.example.demo.dto;

import com.example.demo.model.Korisnik;
import com.example.demo.model.Rezultat;
import com.example.demo.model.Uloga;

public class KorisnikDTO {

    private long id;
    private String ime;
    private String prezime;
    private String password;
    private String email;
    private String brojTelefona;
    private Uloga uloga;


    public KorisnikDTO() {
    }

    public KorisnikDTO(long id, String ime, String prezime, String password, String email, String brojTelefona, Uloga uloga) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.password = password;
        this.email = email;
        this.brojTelefona = brojTelefona;
        this.uloga = uloga;
    }

    public KorisnikDTO(Korisnik k) {
        this(k.getId(),k.getIme(),k.getPrezime(),k.getPassword(),k.getEmail(),k.getBrojTelefona(),k.getUloga());
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
