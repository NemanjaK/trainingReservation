package com.example.demo.service.impl;

import com.example.demo.dto.ReservationDTO;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.mapper.ReservationMapper;
import com.example.demo.model.Reservation;
import com.example.demo.model.Term;
import com.example.demo.model.User;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.TermRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.interfaces.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    ReservationRepository reservationRepository;

    ReservationMapper reservationMapper;

    UserRepository userRepository;

    TermRepository termRepository;

    @Autowired

    public ReservationServiceImpl(ReservationRepository reservationRepository, ReservationMapper reservationMapper,
                                  UserRepository userRepository,
                                  TermRepository termRepository){
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.userRepository = userRepository;
        this.termRepository = termRepository;
    }

    @Override
    public ResponseEntity<?> findAll(Pageable pageable) {
        Page<Reservation> reservations = reservationRepository.findAll(pageable);
        List<ReservationDTO> reservationsDTOS =  new ArrayList<>();

        HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(reservations.getTotalPages()));

        reservations.getContent().forEach(reservation -> reservationsDTOS.add(new ReservationDTO(reservation)));

        return ResponseEntity.ok().headers(headers).body(reservationsDTOS);
    }

    @Override
    public List<ReservationDTO> findAllUsersReservationById(Principal principal) {
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new NotFoundException("User not found!"));

        return reservationRepository.findAllById(user.getId())
                .stream()
                .map(reservationMapper::convertTODto)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDTO findOne(Long id) {
        return reservationRepository.findById(id)
                .map(reservationMapper::convertTODto)
                .orElseThrow(() -> new NotFoundException("Reservation not found!"));
    }

    @Override
    public Reservation save(Long id, Principal principal) {
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new NotFoundException("User not found!"));
        Term term = termRepository.getById(id);

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setTerm(term);
        return reservation;
    }

    @Override
    public void bookReservation(Long id, Principal principal) {
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new NotFoundException("User not found!"));
        Term term = termRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Term not found!"));

        if(term.getOccupancy() < 1){
            throw new NotFoundException("Term is full!");
        }
        term.setOccupancy(term.getOccupancy() - 1);
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setTerm(term);
        reservationRepository.save(reservation);
    }

    @Override
    public void delete(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reservation not found!"));
        reservationRepository.delete(reservation);
    }
}
