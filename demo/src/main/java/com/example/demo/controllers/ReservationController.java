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
    ReservationService rezervacijaService;

    @Autowired
    TermServiceImpl terminService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<ReservationDTO>> getAll(){
        List<Reservation> reservations = rezervacijaService.findAll();
        List<ReservationDTO> reservationsDTOS =  new ArrayList<ReservationDTO>();
        for(Reservation r: reservations){
            reservationsDTOS.add(new ReservationDTO(r));
        }
        return new ResponseEntity<List<ReservationDTO>>(reservationsDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReservationDTO> getOne(@PathVariable("id") Long id){
        Reservation reservation = rezervacijaService.findOne(id);
        ReservationDTO reservationDTO = new ReservationDTO(reservation);


        if(reservation == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return  new ResponseEntity<ReservationDTO>(reservationDTO, HttpStatus.OK);


    }

    @PostMapping(value = "/add")
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setTerm(reservationDTO.getTerm());

        Term term = terminService.findOne(reservationDTO.getTerm().getId());
        int occupancy = term.getOccupancy();

            if ( occupancy < 1) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                term.setOccupancy(occupancy - 1);
                reservation = rezervacijaService.save((reservation));

                return new ResponseEntity<ReservationDTO>(new ReservationDTO(reservation), HttpStatus.CREATED);
            }

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable("id") Long id){
        Reservation reservation = rezervacijaService.findOne(id);
        if(reservation == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        rezervacijaService.delete(reservation);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
