package com.example.demo.service.interfaces;

import com.example.demo.model.Rezervacija;

import java.util.List;

public interface RezervacijaServiceInterface {

    List<Rezervacija> findAll();

    Rezervacija findOne(Long id);

    Rezervacija save(Rezervacija rezervacija);

    void delete(Rezervacija rezervacija);
}
