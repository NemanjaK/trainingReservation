package com.example.demo.service.interfaces;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;

import com.example.demo.security.LoginResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.Optional;

public interface UserService {

    ResponseEntity<?> findAll(Pageable pageable);

    void updateProfile(Long id, UserDTO userDTO);
    UserDTO findOne(Long id);
    User save(User user);
    User getProfile(Principal principal);
    void remove(User user);

    LoginResponse login(LoginDTO loginDTO);
    User addUser(UserDTO userDTO);

    void updateDateExpiration(Long id);

    Optional<User> findByEmail(String email);

}
