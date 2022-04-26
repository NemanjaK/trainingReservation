package com.example.demo.controllers;

import com.example.demo.dto.ReservationDTO;
import com.example.demo.model.Reservation;
import com.example.demo.model.Term;
import com.example.demo.service.impl.ReservationServiceImpl;
import com.example.demo.service.impl.TermServiceImpl;
import com.example.demo.service.interfaces.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    TermServiceImpl termService;

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getAll(){
        List<Reservation> reservations = reservationService.findAll();
        List<ReservationDTO> reservationsDTOS =  new ArrayList<ReservationDTO>();

        reservations.forEach(reservation -> reservationsDTOS.add(new ReservationDTO(reservation)));

        return new ResponseEntity<List<ReservationDTO>>(reservationsDTOS, HttpStatus.OK);
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

    @PostMapping
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO) {
        // razmisli o ovome
        Reservation reservation = new Reservation();

        reservation.setTerm(reservationDTO.getTerm());

        Term term = termService.findOne(reservationDTO.getTerm().getId());
        int occupancy = term.getOccupancy();

            if ( occupancy < 1) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                term.setOccupancy(occupancy - 1);
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
