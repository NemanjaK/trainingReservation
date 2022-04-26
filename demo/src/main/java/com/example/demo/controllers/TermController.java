package com.example.demo.controllers;
import com.example.demo.dto.ReservationDTO;
import com.example.demo.dto.TermDTO;
import com.example.demo.model.Term;
import com.example.demo.service.interfaces.TermService;
import com.sun.tools.jconsole.JConsoleContext;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/terms")
public class TermController {

    LocalDate today = LocalDate.now();
    LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
    LocalDate yesterday = tomorrow.minusDays(2);

    @Autowired
    TermService termService;

    @GetMapping(value = "/today")
    public ResponseEntity<List<TermDTO>> getDayTerms(){
        List<Term> terms = termService.findAllWithDates(yesterday, tomorrow);
        List<TermDTO> termsDTO =  new ArrayList<>();
        terms.forEach(term -> termsDTO.add(new TermDTO(term)));
        return new ResponseEntity<List<TermDTO>>(termsDTO,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<TermDTO>> getAll(){
        List<Term> terms = termService.findAll();
        List<TermDTO> termsDTO =  new ArrayList<>();
        terms.forEach(term -> termsDTO.add(new TermDTO(term)));
        return new ResponseEntity<List<TermDTO>>(termsDTO,HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TermDTO> getOne(@PathVariable("id") Long id){
        Term term = termService.findOne(id);
        TermDTO termDTO = new TermDTO(term);
        if(term == null){
            return new ResponseEntity<>(termDTO, HttpStatus.NOT_FOUND);
        }

        return  new ResponseEntity<TermDTO>(termDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TermDTO> createTerm(@RequestBody TermDTO termDTO){
        Term term = new Term();

        term.setTraining(termDTO.getTraining());
        term.setTime(termDTO.getTime());
        term.setOccupancy(termDTO.getOccupancy());
        term = termService.save(term);

        return  new ResponseEntity<TermDTO>(new TermDTO(term), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "{/id}")
    public ResponseEntity<Void> deleteTerm(@PathVariable("id") Long id){
        Term term = termService.findOne(id);
        if(term == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        termService.delete(term);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
