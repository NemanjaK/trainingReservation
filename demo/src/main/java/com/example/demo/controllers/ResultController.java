package com.example.demo.controllers;
import com.example.demo.dto.ResultDTO;
import com.example.demo.model.Result;
import com.example.demo.service.impl.ResultServiceImpl;
import com.example.demo.service.interfaces.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/results")
public class ResultController {

    @Autowired
    ResultService resultService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<ResultDTO>> getAll(){
        List<Result> results = resultService.findAll();
        List<ResultDTO> resultsDTO =  new ArrayList<ResultDTO>();
        for(Result r: results){
            resultsDTO.add(new ResultDTO(r));
        }
        return new ResponseEntity<List<ResultDTO>>(resultsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResultDTO> getOne(@PathVariable("id") Long id){
        Result result = resultService.findOne(id);
        ResultDTO resultDTO = new ResultDTO(result);
        if(result == null){
            return new ResponseEntity<>(resultDTO, HttpStatus.NOT_FOUND);
        }

        return  new ResponseEntity<ResultDTO>(resultDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<ResultDTO> createTermin (@RequestBody ResultDTO resultDTO){
        Result result = new Result();

        result.setNumbersOfrounds(resultDTO.getNumberOfRunds());
        result.setUser(resultDTO.getUser());
        result.setTraining(resultDTO.getTraining());
        result.setTime(resultDTO.getTime());

        result = resultService.save((result));

        return  new ResponseEntity<ResultDTO>(new ResultDTO(result), HttpStatus.CREATED);
    }
}
