package com.example.demo.service.impl;

import com.example.demo.model.Rezervacija;
import com.example.demo.repository.RezervacijaRepository;
import com.example.demo.service.interfaces.RezervacijaServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RezervacijaService implements RezervacijaServiceInterface {

    @Autowired
    RezervacijaRepository rezervacijaRepository;

    @Override
    public List<Rezervacija> findAll() {
        return rezervacijaRepository.findAll();

    }

    @Override
    public Rezervacija findOne(Long id) {
        return  rezervacijaRepository.getById(id);
    }

    @Override
    public Rezervacija save(Rezervacija rezervacija) {
        return rezervacijaRepository.save(rezervacija);
    }

    @Override
    public void delete(Rezervacija rezervacija) {
        rezervacijaRepository.delete(rezervacija);
    }
}
