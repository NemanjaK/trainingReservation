package com.example.demo.mapper;

import com.example.demo.dto.ReservationDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.Reservation;
import com.example.demo.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    ModelMapper objectMapper = new ModelMapper();

    public UserDTO convertTODto(User user){
        return objectMapper.map(user, UserDTO.class);
    }

    public User convertToEntity(UserDTO userDTO){
        return objectMapper.map(userDTO, User.class);
    }
}
