package com.example.demo.service.impl;

import com.example.demo.model.Korisnik;
import com.example.demo.model.Rezervacija;
import com.example.demo.repository.KorisnikRepository;
import com.example.demo.service.interfaces.KorisnikServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KorisnikService implements KorisnikServiceInterface {

    @Autowired
    KorisnikRepository korisnikRepository;

    @Override
    public List<Korisnik> findAll() {
        return korisnikRepository.findAll();
    }

    @Override
    public Korisnik findOne(Long id) {
        return korisnikRepository.getById(id);
    }

    @Override
    public Korisnik save(Korisnik korisnik) {
        return korisnikRepository.save(korisnik);
    }

    @Override
    public void remove(Korisnik korisnik) {
        korisnikRepository.delete(korisnik);
    }
}
