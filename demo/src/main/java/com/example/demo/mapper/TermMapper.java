package com.example.demo.mapper;

import com.example.demo.controllers.TermController;
import com.example.demo.dto.TermDTO;
import com.example.demo.dto.TrainingDTO;
import com.example.demo.model.Term;
import com.example.demo.model.Training;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TermMapper {

    ModelMapper modelMapper = new ModelMapper();

    public TermDTO convertToDto(Term term){
        return modelMapper.map(term, TermDTO.class);
    }

    public Term convertToEntity(TermDTO termDTO){
        return modelMapper.map(termDTO, Term.class);
    }
}
