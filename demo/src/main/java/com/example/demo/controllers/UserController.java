package com.example.demo.controllers;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.security.LoginResponse;
import com.example.demo.security.TokenHelper;
import com.example.demo.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
public class UserController {


    TokenHelper tokenHelper;
    UserService userService;

    AuthenticationManager authenticationManager;
    UserDetailsService userDetailsService;

    PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager, UserDetailsService userDetailsService, TokenHelper tokenHelper, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.tokenHelper = tokenHelper;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDTO user) {
        return new ResponseEntity<>(userService.login(user), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER','ADMINISTRATOR')")
    @GetMapping("/profile")
    public ResponseEntity<User> getProfile(Principal principal) {
        return new ResponseEntity<>(userService.getProfile(principal), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER','ADMINISTRATOR')")
    @GetMapping
    public ResponseEntity<?> getAll(Pageable pagable) {
        return userService.findAll(pagable);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getOne(@PathVariable("id") Long id) {
        return new ResponseEntity<UserDTO>(userService.findOne(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
        return new ResponseEntity<>("User created!", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateProfile(@PathVariable("id") Long id, @RequestBody UserDTO userDetails) {
        userService.updateProfile(id, userDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/extension/{id}")
    public ResponseEntity<Void> updateDateExpiration(@PathVariable("id") Long id) {
        userService.updateDateExpiration(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
