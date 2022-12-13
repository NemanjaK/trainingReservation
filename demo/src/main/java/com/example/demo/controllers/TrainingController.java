package com.example.demo.controllers;
import com.example.demo.dto.TrainingDTO;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.mapper.ModelMapper;
import com.example.demo.model.Training;
import com.example.demo.service.interfaces.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/trainings")
public class TrainingController {
    TrainingService trainingService;
    ModelMapper modelMapper;

    @Autowired
    public TrainingController(TrainingService trainingService){
        this.trainingService = trainingService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TrainingDTO> getOneTraining(@PathVariable("id") Long id){
        return trainingService.findOne(id)
                .map(training -> new TrainingDTO(training))
                .map(trainingDTO -> new ResponseEntity<>(trainingDTO, HttpStatus.OK))
                .orElseThrow(() -> new NotFoundException("Training not found!"));
    }

    @GetMapping(value = "/datum/{dateOfTraining}")
    public ResponseEntity<TrainingDTO> getTrainingByDate(@PathVariable("dateOfTraining") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate dateOfTraining){
        return trainingService.findTrainingByDateOfTraining(dateOfTraining)
                .map(training -> new TrainingDTO(training))
                .map(trainingDTO -> new ResponseEntity<>(trainingDTO, HttpStatus.OK))
                .orElseThrow(() -> new NotFoundException("Training by date not found!"));
    }
}
