package com.example.demo.service.interfaces;

import com.example.demo.model.Rezultat;

import java.util.List;

public interface RezultatServiceInterface {

    List<Rezultat> findAll();

    Rezultat findOne(Long id);

    Rezultat save(Rezultat rezultat);

    void delete(Rezultat rezultat);
}
