package com.example.demo.controllers;

import com.example.demo.dto.KorisnikDTO;
import com.example.demo.model.Korisnik;
import com.example.demo.service.impl.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/korisnici")
public class KorisnikController {

    @Autowired
    KorisnikService korisnikService;


    @GetMapping(value = "/all")
    public ResponseEntity<List<KorisnikDTO>> getAll(){
        List<Korisnik> korisnici = korisnikService.findAll();
        List<KorisnikDTO> korisniciDTO = new ArrayList<>();
        for(Korisnik k: korisnici){
            korisniciDTO.add(new KorisnikDTO(k));
        }

        return new ResponseEntity<List<KorisnikDTO>>(korisniciDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<KorisnikDTO> getOne(@PathVariable("id") Long id){
        Korisnik korisnik = korisnikService.findOne(id);
        KorisnikDTO korisnikDTO = new KorisnikDTO(korisnik);
        if(korisnik == null){
            return new ResponseEntity<>(korisnikDTO, HttpStatus.NOT_FOUND);
        }

        return  new ResponseEntity<KorisnikDTO>(korisnikDTO, HttpStatus.OK);
    }
}
