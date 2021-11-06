package com.example.demo.controllers;

import com.example.demo.dto.RezultatDTO;
import com.example.demo.dto.TerminDTO;
import com.example.demo.model.Rezervacija;
import com.example.demo.model.Rezultat;
import com.example.demo.model.Termin;
import com.example.demo.service.impl.TerminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/termini")
public class TerminController {

    @Autowired
    TerminService terminService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<TerminDTO>> getAll(){
        List<Termin> termini = terminService.findAll();
        List<TerminDTO> terminiDTO =  new ArrayList<TerminDTO>();
        for(Termin t: termini){
            terminiDTO.add(new TerminDTO(t));
        }
        return new ResponseEntity<List<TerminDTO>>(terminiDTO,HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TerminDTO> getOne(@PathVariable("id") Long id){
        Termin termin = terminService.findOne(id);
        TerminDTO terminDTO = new TerminDTO(termin);
        if(termin == null){
            return new ResponseEntity<>(terminDTO, HttpStatus.NOT_FOUND);
        }

        return  new ResponseEntity<TerminDTO>(terminDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<TerminDTO> createTermin(@RequestBody TerminDTO terminDTO){
        Termin termin = new Termin();

        termin.setTrening(terminDTO.getTrening());
        termin.setVreme(terminDTO.getVreme());
        termin.setBrojMesta(terminDTO.getBrojMesta());
        terminService.save(termin);

        return  new ResponseEntity<TerminDTO>(new TerminDTO(termin), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "{/id}")
    public ResponseEntity<Void> deleteTermin(@PathVariable("id") Long id){
        Termin termin = terminService.findOne(id);
        if(termin == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        terminService.delete(termin);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
