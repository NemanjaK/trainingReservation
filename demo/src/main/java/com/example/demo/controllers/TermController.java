package com.example.demo.controllers;
import com.example.demo.dto.ReservationDTO;
import com.example.demo.dto.TermDTO;
import com.example.demo.exceptions.NotFoundException;
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
import java.time.LocalDateTime;
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

    public static final LocalDate NOW = LocalDate.now();
    public static final LocalDate TOMORROW = NOW.plus(1, ChronoUnit.DAYS);
    public static final LocalDate YESTERDAY = TOMORROW.minusDays(2);

    TermService termService;
    @Autowired
    public TermController(TermService termService){
        this.termService = termService;
    }

    @GetMapping(value = "/today")
    public ResponseEntity<List<TermDTO>> getDayTerms(){
        List<Term> terms = termService.findAllWithDates(YESTERDAY, TOMORROW);
        List<TermDTO> termsDTO =  new ArrayList<>();
        terms.forEach(term -> termsDTO.add(new TermDTO(term)));
        return new ResponseEntity<>(termsDTO,HttpStatus.OK);
    }

    @GetMapping(value = "/termsByDay/{day}")
    public ResponseEntity<List<TermDTO>> getTermsByDay(@PathVariable("day") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate day){
        List<Term> terms = termService.findAllByDay(day);
        List<TermDTO> termsDTO =  new ArrayList<>();
        terms.forEach(term -> termsDTO.add(new TermDTO(term)));
        return new ResponseEntity<>(termsDTO,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<TermDTO>> getAll(){
        List<Term> terms = termService.findAll();
        List<TermDTO> termsDTO =  new ArrayList<>();
        terms.forEach(term -> termsDTO.add(new TermDTO(term)));
        return new ResponseEntity<>(termsDTO,HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TermDTO> getOneTerm(@PathVariable("id") Long id){
        return termService.findOne(id)
                .map(term -> new TermDTO(term))
                .map(termDTO -> new ResponseEntity<TermDTO>(termDTO, HttpStatus.OK))
                .orElseThrow(() -> new NotFoundException("Term not found!"));
    }

    @PostMapping
    public ResponseEntity<TermDTO> createTerm(@RequestBody TermDTO termDTO){

        Term term = new Term();
        term.setTime(termDTO.getTime());
        term.setOccupancy(termDTO.getOccupancy());
        term.setTypeOfTraining(termDTO.getTypeOfTraining());
        term = termService.save(term);

        return  new ResponseEntity<>(new TermDTO(term), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTerm(@PathVariable("id") Long id){
        Term term = termService.findOne(id)
                .orElseThrow(() -> new NotFoundException("Term not found!"));
        termService.delete(term);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
