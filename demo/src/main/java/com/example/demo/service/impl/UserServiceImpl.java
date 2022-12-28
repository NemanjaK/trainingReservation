package com.example.demo.service.impl;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.LoginResponse;
import com.example.demo.security.TokenHelper;
import com.example.demo.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.controllers.TermController.NOW;

@Service
public class UserServiceImpl implements UserService {

    public static final LocalDate NEXT_MONTH = NOW.plus(1, ChronoUnit.MONTHS);
    UserRepository userRepository;

    UserMapper userMapper;

    AuthenticationManager authenticationManager;
    UserDetailsService userDetailsService;

    PasswordEncoder passwordEncoder;

    TokenHelper tokenHelper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, AuthenticationManager authenticationManager,
                           UserDetailsService userDetailsService, TokenHelper tokenHelper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.tokenHelper = tokenHelper;
    }

    @Override
    public ResponseEntity<?> findAll(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        List<UserDTO> usersDTO = new ArrayList<>();

        HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(users.getTotalPages()));

        users.getContent().forEach(user -> usersDTO.add(new UserDTO(user)));

        return ResponseEntity.ok().headers(headers).body(usersDTO);
    }

    @Override
    public void updateProfile(Long id, UserDTO userDTO) {
          userRepository.findById(id)
                  .orElseThrow(() -> new NotFoundException("User not found!"));
        userRepository.save(userMapper.convertToEntity(userDTO));
    }

    @Override
    public UserDTO findOne(Long id) {
        return userRepository.findById(id)
                .map(userMapper::convertTODto)
                .orElseThrow(() -> new NotFoundException("User not found!"));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getProfile(Principal principal) {
        return userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new NotFoundException("User not found1"));
    }

    @Override
    public void remove(User user) {
        userRepository.delete(user);
    }

    @Override
    public LoginResponse login(LoginDTO loginDTO) {
        User initUser = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found!"));
        if (initUser.isStatus() == false) {
            return loginNoActiveUser(initUser, loginDTO);
        }
        return loginActiveUser(loginDTO);
    }

    @Override
    public User addUser(UserDTO userDTO) {
        userRepository.findByEmail(userDTO.getEmail())
                .ifPresent(s -> {
                    throw new BadRequestException("User already exists!");
                });
        userDTO.setMembershipExpirationDate(NEXT_MONTH);
        userDTO.setStatus(false);
        return userRepository.save(userMapper.convertToEntity(userDTO));
    }

    @Override
    public void updateDateExpiration(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found!"));
        user.setMembershipExpirationDate(NEXT_MONTH);
        userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    LoginResponse loginNoActiveUser(User initUser, LoginDTO loginDTO){
        initUser.setPassword(passwordEncoder.encode(loginDTO.getPassword()));
        initUser.setStatus(true);
        userRepository.save(initUser);
        UserDetails details = userRepository.findUserDetailsByEmail(loginDTO.getEmail());
        return new LoginResponse(tokenHelper.generateToken(details), initUser.getEmail(), initUser.getRole().getAuthority(), initUser.getMembershipExpirationDate());
    }

    LoginResponse loginActiveUser(LoginDTO loginDTO){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());
        authenticationManager.authenticate(token);
        UserDetails details = userRepository.findUserDetailsByEmail(loginDTO.getEmail());
        User userData = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new NotFoundException("Training by date not found!"));
        return new LoginResponse(tokenHelper.generateToken(details), userData.getEmail(), userData.getRole().getAuthority(), userData.getMembershipExpirationDate());
    }
}
