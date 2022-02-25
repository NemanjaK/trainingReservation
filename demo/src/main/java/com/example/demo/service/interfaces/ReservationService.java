package com.example.demo.service.interfaces;

import com.example.demo.model.Reservation;
import com.example.demo.model.Reservation;

import java.util.List;

public interface ReservationService {

    List<Reservation> findAll();

    Reservation findOne(Long id);

    Reservation save(Reservation reservation);

    void delete(Reservation reservation);
}
