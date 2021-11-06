package com.example.demo.dto;

import com.example.demo.model.Termin;
import com.example.demo.model.Trening;

import java.util.Date;

public class TerminDTO {

    private long id;
    private Date vreme;
    private int brojMesta;
    private Trening trening;

    public TerminDTO(Termin t) {
        this(t.getId(),t.getVreme(),t.getBrojMesta(),t.getTrening());
    }

    public TerminDTO(long id, Date vreme, int brojMesta, Trening trening) {
        this.id = id;
        this.vreme = vreme;
        this.brojMesta = brojMesta;
        this.trening = trening;
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

    public int getBrojMesta() {
        return brojMesta;
    }

    public void setBrojMesta(int brojMesta) {
        this.brojMesta = brojMesta;
    }

    public void setTrening(Trening trening) {
        this.trening = trening;
    }
}
