package com.example.demo.service.interfaces;

import com.example.demo.model.Termin;
import com.example.demo.model.Trening;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface TreningServiceInterface {

    List<Trening> findAll();

    Trening findOne(Long id);

    Trening save(Trening trening);

    void delete(Trening trening);

    Trening findTreningByDatumTreninga(LocalDate datumTreninga);
}
