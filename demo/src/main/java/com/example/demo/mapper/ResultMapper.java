package com.example.demo.mapper;

import com.example.demo.dto.ResultDTO;
import com.example.demo.model.Result;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ResultMapper {

    ModelMapper modelMapper = new ModelMapper();

    public ResultDTO convertToDto(Result result) {
       return modelMapper.map(result, ResultDTO.class);
    }

    public Result convertToEntity(ResultDTO resultDTO){

        return modelMapper.map(resultDTO, Result.class);
    }
}
