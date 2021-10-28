package com.example.demo.dto;

import com.example.demo.model.Trening;

public class TreningDTO {

    private Long id;
    private String opis;

    public TreningDTO() {
    }

    public TreningDTO(Long id, String opis) {
        this.id = id;
        this.opis = opis;
    }

    public TreningDTO(Trening t) {
        this(t.getId(),t.getOpis());
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
}
