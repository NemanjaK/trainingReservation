package com.example.demo.controllers;
import com.example.demo.dto.TermDTO;
import com.example.demo.model.Term;
import com.example.demo.service.interfaces.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/terms")
public class TermController {

    @Autowired
    TermService termService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<TermDTO>> getAll(){
        List<Term> terms = termService.findAll();
        List<TermDTO> termsDTO =  new ArrayList<TermDTO>();
        for(Term t: terms){
            termsDTO.add(new TermDTO(t));
        }
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

    @PostMapping(value = "/add")
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
