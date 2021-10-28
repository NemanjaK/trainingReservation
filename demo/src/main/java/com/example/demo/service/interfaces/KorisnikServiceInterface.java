package com.example.demo.service.interfaces;

import com.example.demo.model.Korisnik;
import com.example.demo.model.Rezervacija;

import java.util.List;

public interface KorisnikServiceInterface {

    List<Korisnik> findAll();
    Korisnik findOne(Long id);
    Korisnik save(Korisnik korisnik);
    void remove(Korisnik korisnik);

}
