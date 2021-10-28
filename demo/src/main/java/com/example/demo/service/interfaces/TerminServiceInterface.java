package com.example.demo.service.interfaces;

import com.example.demo.model.Rezultat;
import com.example.demo.model.Termin;

import java.util.List;

public interface TerminServiceInterface {
    List<Termin> findAll();

    Termin findOne(Long id);

    Termin save(Termin termin);

    void delete(Termin termin);
}
