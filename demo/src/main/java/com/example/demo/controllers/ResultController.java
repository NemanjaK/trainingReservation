package com.example.demo.controllers;
import com.example.demo.dto.ResultDTO;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Result;
import com.example.demo.model.User;
import com.example.demo.service.interfaces.ResultService;
import com.example.demo.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/results")
public class ResultController {

    @Autowired
    ResultService resultService;

    @Autowired
    UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResultDTO> getOneResult(@PathVariable("id") Long id){
        return resultService.findOne(id)
                .map(result -> new ResultDTO(result))
                .map(result -> new ResponseEntity<>(result, HttpStatus.OK))
                .orElseThrow(() -> new NotFoundException("Result with " + id + " not  found!"));
    }

    @PostMapping(value = "/add")
    public void addResult(@RequestBody ResultDTO resultDTO, Principal principal){
        Optional<User> user = Optional.ofNullable(userService.findByEmail(principal.getName())
                .orElseThrow(() -> new NotFoundException("User not found")));

        Result result = new Result();
        result.setNumbersOfrounds(resultDTO.getNumberOfRunds());
        result.setUser(user.get());
        result.setTime(resultDTO.getTime());
        resultService.save((result));
    }
}
