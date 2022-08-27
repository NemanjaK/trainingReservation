package com.example.demo.controllers;
import com.example.demo.dto.TrainingDTO;
import com.example.demo.model.Training;
import com.example.demo.service.interfaces.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/trainings")
public class TrainingController {

    @Autowired
    TrainingService trainingService;


    @GetMapping(value = "/{id}")
    public ResponseEntity<TrainingDTO> getOne(@PathVariable("id") Long id){
        Training training = trainingService.findOne(id);
        TrainingDTO trainingDTO = new TrainingDTO(training);
        if(training == null){
            return new ResponseEntity<>(trainingDTO, HttpStatus.NOT_FOUND);
        }

        return  new ResponseEntity<TrainingDTO>(trainingDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/datum/{dateOfTraining}")
    public ResponseEntity<TrainingDTO> getTrainingByDate(@PathVariable("dateOfTraining") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate dateOfTraining){
        Training training = trainingService.findTrainingByDateOfTraining(dateOfTraining);;
        System.out.println(training);
        TrainingDTO trainingDTO = new TrainingDTO(training);
        if(training == null){
            return new ResponseEntity<>(trainingDTO, HttpStatus.NOT_FOUND);
        }

        return  new ResponseEntity<TrainingDTO>(trainingDTO, HttpStatus.OK);
    }
}
