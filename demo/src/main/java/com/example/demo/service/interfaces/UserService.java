package com.example.demo.service.interfaces;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    User findOne(Long id);
    User save(User user);
    void remove(User user);

    User addUser(UserDTO userDTO);

    User findByEmail(String email);

}
