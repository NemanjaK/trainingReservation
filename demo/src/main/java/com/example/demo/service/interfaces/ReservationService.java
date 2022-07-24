package com.example.demo.service.interfaces;

import com.example.demo.model.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReservationService {

    Page<Reservation> findAll(Pageable pageable);

    Reservation findOne(Long id);

    Reservation save(Reservation reservation);

    void delete(Reservation reservation);
}
