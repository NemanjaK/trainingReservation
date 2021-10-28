package com.example.demo.service.impl;

import com.example.demo.model.Rezultat;
import com.example.demo.repository.RezervacijaRepository;
import com.example.demo.repository.RezultatRepository;
import com.example.demo.service.interfaces.RezultatServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RezultatService implements RezultatServiceInterface {

    @Autowired
    RezultatRepository rezultatRepository;

    @Override
    public List<Rezultat> findAll() {
        return rezultatRepository.findAll();
    }

    @Override
    public Rezultat findOne(Long id) {
        return rezultatRepository.getById(id);
    }

    @Override
    public Rezultat save(Rezultat rezultat) {
        return  rezultatRepository.save(rezultat);
    }

    @Override
    public void delete(Rezultat rezultat) {
        rezultatRepository.delete(rezultat);
    }
}
