package com.example.demo.service.impl;

import com.example.demo.dto.UserDTO;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import static com.example.demo.controllers.TermController.NOW;

@Service
public class UserServiceImpl implements UserService {

    public static final LocalDate NEXT_MONTH = NOW.plus(1 , ChronoUnit.MONTHS);
    UserRepository userRepository;

    UserMapper userMapper;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findOne(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void remove(User user) {
        userRepository.delete(user);
    }

    @Override
    public User addUser(UserDTO userDTO) {
        userDTO.setMembershipExpirationDate(NEXT_MONTH);
        userDTO.setStatus(false);
        return userRepository.save(userMapper.convertToEntity(userDTO));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
