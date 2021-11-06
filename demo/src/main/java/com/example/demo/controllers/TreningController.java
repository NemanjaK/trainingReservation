package com.example.demo.controllers;

import com.example.demo.dto.RezervacijeDTO;
import com.example.demo.dto.TerminDTO;
import com.example.demo.dto.TreningDTO;
import com.example.demo.model.Rezervacija;
import com.example.demo.model.Termin;
import com.example.demo.model.Trening;
import com.example.demo.service.impl.TerminService;
import com.example.demo.service.impl.TreningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/treninzi")
public class TreningController {

    @Autowired
    TreningService treningService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<TreningDTO>> getAll(){
        List<Trening> treninzi = treningService.findAll();
        List<TreningDTO> treninziDTO =  new ArrayList<TreningDTO>();
        for(Trening t: treninzi){
            treninziDTO.add(new TreningDTO(t));
        }
        return new ResponseEntity<List<TreningDTO>>(treninziDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TreningDTO> getOne(@PathVariable("id") Long id){
        Trening trening = treningService.findOne(id);
        TreningDTO treningDTO = new TreningDTO(trening);
        if(trening == null){
            return new ResponseEntity<>(treningDTO, HttpStatus.NOT_FOUND);
        }

        return  new ResponseEntity<TreningDTO>(treningDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/datum/{datumTreninga}")
    public ResponseEntity<TreningDTO> getTreningByDate(@PathVariable("datumTreninga") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate datumTreninga){
        Trening trening = treningService.findTreningByDatumTreninga(datumTreninga);;
        TreningDTO treningDTO = new TreningDTO(trening);
        if(trening == null){
            return new ResponseEntity<>(treningDTO, HttpStatus.NOT_FOUND);
        }

        return  new ResponseEntity<TreningDTO>(treningDTO, HttpStatus.OK);
    }
    @PostMapping(value = "/add")
    public ResponseEntity<TreningDTO> createReservation(@RequestBody TreningDTO treningDTO){
        Trening trening = new Trening();

        trening.setOpis(treningDTO.getOpis());
        trening.setTipTreninga(treningDTO.getTipTreninga());
        trening.setTipTreninga(treningDTO.getTipTreninga());

        treningService.save(trening);
        return  new ResponseEntity<TreningDTO>(new TreningDTO(trening), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable("id") Long id){
        Trening trening = treningService.findOne(id);
        if(trening == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        treningService.delete(trening);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
