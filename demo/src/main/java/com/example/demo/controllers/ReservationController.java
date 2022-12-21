package com.example.demo.controllers;

import com.example.demo.dto.ReservationDTO;
import com.example.demo.mapper.ReservationMapper;
import com.example.demo.mapper.TermMapper;
import com.example.demo.service.interfaces.ReservationService;
import com.example.demo.service.interfaces.TermService;
import com.example.demo.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    ReservationService reservationService;

    UserService userService;

    TermService termService;

    TermMapper termMapper;

    ReservationMapper reservationMapper;

    @Autowired
    public ReservationController(ReservationService reservationService, UserService userService,
                                 TermMapper termMapper,
                                 ReservationMapper reservationMapper,
                                 TermService termService){
        this.reservationService = reservationService;
        this.userService = userService;
        this.termService = termService;
        this.termMapper = termMapper;
        this.reservationMapper = reservationMapper;
    }

    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable){
        return reservationService.findAll(pageable);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReservationDTO> getOne(@PathVariable("id") Long id){
        return new ResponseEntity<>(reservationService.findOne(id), HttpStatus.OK);
    }

    @GetMapping(value = "/myReservation")
    public ResponseEntity<List<ReservationDTO>> myReservation(Principal p){
      return new ResponseEntity<>(reservationService.findAllUsersReservationById(p), HttpStatus.OK);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Void> createReservation(@PathVariable("id") Long id, Principal principal) {
       reservationService.bookReservation(id, principal);
       return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable("id") Long id){
        reservationService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
