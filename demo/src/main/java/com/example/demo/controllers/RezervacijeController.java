package com.example.demo.controllers;

import com.example.demo.dto.RezervacijeDTO;
import com.example.demo.model.Rezervacija;
import com.example.demo.model.Termin;
import com.example.demo.service.impl.RezervacijaService;
import com.example.demo.service.impl.TerminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/rezervacije")
public class RezervacijeController {

    @Autowired
    RezervacijaService rezervacijaService;

    @Autowired
    TerminService terminService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<RezervacijeDTO>> getAll(){
        List<Rezervacija> rezervacije = rezervacijaService.findAll();
        List<RezervacijeDTO> rezervacijeDTO =  new ArrayList<RezervacijeDTO>();
        for(Rezervacija r: rezervacije){
            rezervacijeDTO.add(new RezervacijeDTO(r));
        }
        return new ResponseEntity<List<RezervacijeDTO>>(rezervacijeDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RezervacijeDTO> getOne(@PathVariable("id") Long id){
        Rezervacija rezervacija = rezervacijaService.findOne(id);
        RezervacijeDTO rezervacijaDTO = new RezervacijeDTO(rezervacija);


        if(rezervacija == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return  new ResponseEntity<RezervacijeDTO>(rezervacijaDTO, HttpStatus.OK);


    }

    @PostMapping(value = "/add")
    public ResponseEntity<RezervacijeDTO> createReservation(@RequestBody RezervacijeDTO rezervacijeDTO) {
        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setTermin(rezervacijeDTO.getTermin());

        Termin termin = terminService.findOne(rezervacijeDTO.getTermin().getId());
        int brojMesta = termin.getBrojMesta();

            if ( brojMesta < 1) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                termin.setBrojMesta(brojMesta - 1);
                rezervacija = rezervacijaService.save((rezervacija));

                return new ResponseEntity<RezervacijeDTO>(new RezervacijeDTO(rezervacija), HttpStatus.CREATED);
            }

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable("id") Long id){
        Rezervacija rezervacija = rezervacijaService.findOne(id);
        if(rezervacija == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        rezervacijaService.delete(rezervacija);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
