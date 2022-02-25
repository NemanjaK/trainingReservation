package com.example.demo.controllers;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/korisnici")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping(value = "/all")
    public ResponseEntity<List<UserDTO>> getAll(){
        List<User> users = userService.findAll();
        List<UserDTO> usersDTO = new ArrayList<>();
        for(User k: users){
            usersDTO.add(new UserDTO(k));
        }

        return new ResponseEntity<List<UserDTO>>(usersDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getOne(@PathVariable("id") Long id){
        User user = userService.findOne(id);
        UserDTO userDTO = new UserDTO(user);
        if(user == null){
            return new ResponseEntity<>(userDTO, HttpStatus.NOT_FOUND);
        }

        return  new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
    }

}
