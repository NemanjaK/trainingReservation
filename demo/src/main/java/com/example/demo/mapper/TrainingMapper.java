package com.example.demo.mapper;
import org.modelmapper.ModelMapper;
import com.example.demo.dto.TrainingDTO;
import com.example.demo.model.Training;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class TrainingMapper {

    ModelMapper modelMapper = new ModelMapper();

    public TrainingDTO convertToDto(Training training){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(training, TrainingDTO.class);
    }

    public Training convertToEntity(TrainingDTO trainingDTO){
        return modelMapper.map(trainingDTO, Training.class);
    }
}
