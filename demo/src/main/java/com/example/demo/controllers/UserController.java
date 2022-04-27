package com.example.demo.controllers;
import antlr.Token;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.LoginResponse;
import com.example.demo.security.TokenHelper;
import com.example.demo.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {


    TokenHelper tokenHelper;
    UserService userService;

    AuthenticationManager authenticationManager;
    UserDetailsService userDetailsService;

    PasswordEncoder passwordEncoder;

    UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager, UserDetailsService userDetailsService, TokenHelper tokenHelper, PasswordEncoder passwordEncoder, UserRepository userRepository){
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.tokenHelper = tokenHelper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO user) {
        User initUser = userService.findByEmail(user.getEmail());
        if(initUser != null && initUser.isStatus() == false){
            initUser.setPassword(passwordEncoder.encode(user.getPassword()));
            initUser.setStatus(true);
            userRepository.save(initUser);
            UserDetails details = userDetailsService.loadUserByUsername(user.getEmail());
            return ResponseEntity.ok(new LoginResponse(tokenHelper.generateToken(details), initUser.getEmail(), initUser.getRole().getAuthority()));
        }
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
            authenticationManager.authenticate(token);
            UserDetails details = userDetailsService.loadUserByUsername(user.getEmail());
            User userData = userService.findByEmail(user.getEmail());
            return ResponseEntity.ok(new LoginResponse(tokenHelper.generateToken(details), userData.getEmail(), userData.getRole().getAuthority()));
        } catch (UsernameNotFoundException e) {
            e.getMessage();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll(){
        List<User> users = userService.findAll();
        List<UserDTO> usersDTO = new ArrayList<>();
        users.forEach(user -> usersDTO.add(new UserDTO(user)));

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

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO){
        User user = userService.addUser(userDTO);

        return new ResponseEntity<User>(user, HttpStatus.OK) ;
    }

}
