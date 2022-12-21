package com.example.demo.controllers;
import com.example.demo.dto.ResultDTO;
import com.example.demo.service.interfaces.ResultService;
import com.example.demo.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/results")
public class ResultController {

    ResultService resultService;

    UserService userService;

    @Autowired
    public ResultController(ResultService resultService, UserService userService){
        this.resultService = resultService;
        this.userService = userService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResultDTO> getOneResult(@PathVariable("id") Long id){
        return new ResponseEntity<>(resultService.findOne(id), HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Void> addResult(@RequestBody ResultDTO resultDTO, Principal principal){
        resultService.save(resultDTO, principal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
