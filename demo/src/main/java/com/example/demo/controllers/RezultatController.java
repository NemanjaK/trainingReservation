package com.example.demo.controllers;

import com.example.demo.dto.KorisnikDTO;
import com.example.demo.dto.RezervacijeDTO;
import com.example.demo.dto.RezultatDTO;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Rezervacija;
import com.example.demo.model.Rezultat;
import com.example.demo.service.impl.RezultatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/rezultati")
public class RezultatController {

    @Autowired
    RezultatService rezultatService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<RezultatDTO>> getAll(){
        List<Rezultat> rezultati = rezultatService.findAll();
        List<RezultatDTO> rezultatiDTO =  new ArrayList<RezultatDTO>();
        for(Rezultat r: rezultati){
            rezultatiDTO.add(new RezultatDTO(r));
        }
        return new ResponseEntity<List<RezultatDTO>>(rezultatiDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RezultatDTO> getOne(@PathVariable("id") Long id){
        Rezultat rezultat = rezultatService.findOne(id);
        RezultatDTO rezultatDTO = new RezultatDTO(rezultat);
        if(rezultat == null){
            return new ResponseEntity<>(rezultatDTO, HttpStatus.NOT_FOUND);
        }

        return  new ResponseEntity<RezultatDTO>(rezultatDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<RezultatDTO> createTermin (@RequestBody RezultatDTO rezultatDTO){
        Rezultat rezultat = new Rezultat();

        rezultat.setBrojRundi(rezultatDTO.getBrojRundi());
        rezultat.setKorisnik(rezultatDTO.getKorisnik());
        rezultat.setTrening(rezultatDTO.getTrening());
        rezultat.setVreme(rezultatDTO.getVreme());

        rezultat = rezultatService.save((rezultat));

        return  new ResponseEntity<RezultatDTO>(new RezultatDTO(rezultat), HttpStatus.CREATED);
    }
}
