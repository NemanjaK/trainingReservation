package com.example.demo.controllers;
import com.example.demo.dto.TrainingDTO;
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
    TrainingService trainingService;

    @Autowired
    public TrainingController(TrainingService trainingService){
        this.trainingService = trainingService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TrainingDTO> getOneTraining(@PathVariable("id") Long id){
        return new ResponseEntity<>(trainingService.findOne(id),HttpStatus.OK);
    }

    @GetMapping(value = "/date/{dateOfTraining}")
    public ResponseEntity<TrainingDTO> getTrainingByDate(@PathVariable("dateOfTraining")
                                                         @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate dateOfTraining){
        return new ResponseEntity<>(trainingService.findTrainingByDateOfTraining(dateOfTraining), HttpStatus.OK);
    }
}
