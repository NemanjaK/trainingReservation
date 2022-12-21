package com.example.demo.controllers;
import com.example.demo.dto.TermDTO;
import com.example.demo.service.interfaces.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

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
        return new ResponseEntity<>(termService.getTodayTerms(YESTERDAY, TOMORROW),HttpStatus.OK);
    }

    @GetMapping(value = "/termsByDay/{day}")
    public ResponseEntity<List<TermDTO>> getTermsByDay(@PathVariable("day") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate day){
        return new ResponseEntity<>(termService.findAllByDay(day), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<TermDTO>> getAllTerms(){
        return new ResponseEntity<>(termService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TermDTO> getOneTerm(@PathVariable("id") Long id){
        return new ResponseEntity<>(termService.findOne(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createTerm(@RequestBody TermDTO termDTO){
        termService.save(termDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTerm(@PathVariable("id") Long id){
       termService.delete(id);
       return new ResponseEntity<>(HttpStatus.OK);
    }
}
