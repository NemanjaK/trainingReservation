package com.example.demo.dto;

import com.example.demo.model.Trening;

import java.time.LocalDate;
import java.util.Date;

public class TreningDTO {

    private Long id;
    private String opis;
    private String tipTreninga;
    private LocalDate datumTreninga;

    public TreningDTO() {
    }

    public TreningDTO(Long id, String opis,String tipTreninga,LocalDate datumTreninga) {
        this.id = id;
        this.opis = opis;
        this.tipTreninga = tipTreninga;
        this.datumTreninga = datumTreninga;
    }

    public TreningDTO(Trening t) {
        this(t.getId(),t.getOpis(),t.getTipTreninga(), t.getDatumTreninga());
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

    public String getTipTreninga() {
        return tipTreninga;
    }

    public void setTipTreninga(String tipTreninga) {
        this.tipTreninga = tipTreninga;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public LocalDate getDatumTreninga() {
        return datumTreninga;
    }

    public void setDatumTreninga(LocalDate datumTreninga) {
        this.datumTreninga = datumTreninga;
    }
}
