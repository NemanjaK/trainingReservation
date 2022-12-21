package com.example.demo.service.interfaces;

import com.example.demo.dto.ReservationDTO;
import com.example.demo.model.Reservation;
import org.modelmapper.internal.Pair;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;

public interface ReservationService {

    ResponseEntity<?> findAll(Pageable pageable);

    List<ReservationDTO> findAllUsersReservationById(Principal principal);

    ReservationDTO findOne(Long id);

    Reservation save(Long id, Principal principal);

    void bookReservation(Long id, Principal principal);

    void delete(Long id);
}
