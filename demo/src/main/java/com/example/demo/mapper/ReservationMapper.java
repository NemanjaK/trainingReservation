package com.example.demo.mapper;

import com.example.demo.dto.ReservationDTO;
import com.example.demo.model.Reservation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

    ModelMapper objectMapper = new ModelMapper();

    public ReservationDTO convertTODto(Reservation reservation){
        return objectMapper.map(reservation, ReservationDTO.class);
    }
}
