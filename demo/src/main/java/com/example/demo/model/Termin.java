package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Termin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date vreme;
    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private Trening trening;
    @JsonIgnore
    @OneToMany(mappedBy = "termin")
    private List<Rezervacija> rezervacije;

    public Termin() {
    }

    public Termin(long id, Date vreme, Trening trening, List<Rezervacija> rezervacije) {
        this.id = id;
        this.vreme = vreme;
        this.trening = trening;
        this.rezervacije = rezervacije;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getVreme() {
        return vreme;
    }

    public void setVreme(Date vreme) {
        this.vreme = vreme;
    }

    public Trening getTrening() {
        return trening;
    }

    public void setTrening(Trening trening) {
        this.trening = trening;
    }

    public List<Rezervacija> getRezervacije() {
        return rezervacije;
    }

    public void setRezervacije(List<Rezervacija> rezervacije) {
        this.rezervacije = rezervacije;
    }
}
