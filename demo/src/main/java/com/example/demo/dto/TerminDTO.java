package com.example.demo.dto;

import com.example.demo.model.Termin;
import com.example.demo.model.Trening;

import java.util.Date;

public class TerminDTO {

    private long id;
    private Date vreme;
    private Trening trening;

    public TerminDTO(Termin t) {
        this(t.getId(),t.getVreme(),t.getTrening());
    }

    public TerminDTO(long id, Date vreme, Trening trening) {
        this.id = id;
        this.vreme = vreme;
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

    public void setTrening(Trening trening) {
        this.trening = trening;
    }
}
