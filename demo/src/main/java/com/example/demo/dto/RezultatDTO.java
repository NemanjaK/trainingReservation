package com.example.demo.dto;

import com.example.demo.model.Korisnik;
import com.example.demo.model.Rezultat;
import com.example.demo.model.Trening;

public class RezultatDTO {

    private Long id;
    private String vreme;
    private String brojRundi;
    private Korisnik korisnik;
    private Trening trening;

    public RezultatDTO(Rezultat r) {
        this(r.getId(),r.getVreme(), r.getBrojRundi(),r.getKorisnik(),r.getTrening());
    }

    public RezultatDTO(Long id, String vreme, String brojRundi, Korisnik korisnik, Trening trening) {
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
