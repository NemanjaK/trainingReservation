package com.example.demo.service.interfaces;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface UserService {

    Page<User> findAll(Pageable pageable);
    User findOne(Long id);
    User save(User user);
    void remove(User user);

    User addUser(UserDTO userDTO);

    User findByEmail(String email);

}
