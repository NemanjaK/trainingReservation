package com.example.demo.controllers;

import com.example.demo.dto.ReservationDTO;
import com.example.demo.model.Reservation;
import com.example.demo.model.Term;
import com.example.demo.model.User;
import com.example.demo.service.interfaces.ReservationService;
import com.example.demo.service.interfaces.TermService;
import com.example.demo.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    UserService userService;

    @Autowired
    TermService termService;

    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable){
        Page<Reservation> reservations = reservationService.findAll(pageable);
        List<ReservationDTO> reservationsDTOS =  new ArrayList<ReservationDTO>();
        HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(reservations.getTotalPages()));
        reservations.getContent().forEach(reservation -> reservationsDTOS.add(new ReservationDTO(reservation)));

        return  ResponseEntity.ok().headers(headers).body(reservationsDTOS);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReservationDTO> getOne(@PathVariable("id") Long id){
        Reservation reservation = reservationService.findOne(id);
        ReservationDTO reservationDTO = new ReservationDTO(reservation);

        if(reservation == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<ReservationDTO>(reservationDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/myReservation")
    public ResponseEntity<List<ReservationDTO>> myReservation(Principal p){
        User user = userService.findByEmail(p.getName());
        Long userId = user.getId();

        List<Reservation> reservations =  reservationService.findAllById(userId);
        List<ReservationDTO> reservationDTOS = new ArrayList<>();

        reservations.forEach(reservation -> reservationDTOS.add(new ReservationDTO(reservation)));

        return new ResponseEntity<List<ReservationDTO>>(reservationDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<ReservationDTO> createReservation(@PathVariable("id") Long id, Principal p) {

        User user = userService.findByEmail(p.getName());
        Term term = termService.findById(id);

        Reservation reservation = new Reservation();
        reservation.setTerm(term);
        reservation.setUser(user);

        int occupancy = term.getOccupancy();
            if ( occupancy < 1) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                term.setOccupancy(occupancy - 1);
                termService.save(term);
                reservation = reservationService.save((reservation));
                return new ResponseEntity<ReservationDTO>(new ReservationDTO(reservation), HttpStatus.CREATED);
            }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable("id") Long id){
        Reservation reservation = reservationService.findOne(id);
        if(reservation == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        reservationService.delete(reservation);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
