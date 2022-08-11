//package com.example.demo.controllers;
//
//import com.example.demo.dto.TrainingDTO;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/trainings")
//public class TrainingController {
//
//    @Autowired
//    TrainingService trainingService;
//
//
//    @GetMapping
//    public ResponseEntity<List<TrainingDTO>> getAll(@RequestParam(name = "dateOfTraining", defaultValue = "")  @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate dateOfTraining){
//        List<Training> trainings = trainingService.findAllByDateOfTraining(dateOfTraining);
//        List<TrainingDTO> trainingsDTO =  new ArrayList<TrainingDTO>();
//        trainings.forEach(training -> trainingsDTO.add(new TrainingDTO(training)));
//        return new ResponseEntity<List<TrainingDTO>>(trainingsDTO, HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/{id}")
//    public ResponseEntity<TrainingDTO> getOne(@PathVariable("id") Long id){
//        Training training = trainingService.findOne(id);
//        TrainingDTO trainingDTO = new TrainingDTO(training);
//        if(training == null){
//            return new ResponseEntity<>(trainingDTO, HttpStatus.NOT_FOUND);
//        }
//
//        return  new ResponseEntity<TrainingDTO>(trainingDTO, HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/datum/{dateOfTraining}")
//    public ResponseEntity<TrainingDTO> getTrainingByDate(@PathVariable("dateOfTraining") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate dateOfTraining){
//        Training training = trainingService.findTrainingByDateOfTraining(dateOfTraining);;
//        System.out.println(training);
//        TrainingDTO trainingDTO = new TrainingDTO(training);
//        if(training == null){
//            return new ResponseEntity<>(trainingDTO, HttpStatus.NOT_FOUND);
//        }
//
//        return  new ResponseEntity<TrainingDTO>(trainingDTO, HttpStatus.OK);
//    }
//    @PostMapping
//    public ResponseEntity<TrainingDTO> createReservation(@RequestBody TrainingDTO treningDTO){
//        Training training = new Training();
//
//        training.setDescription(treningDTO.getDescription());
//        training.setTypeOfTraining(treningDTO.getTypeOfTraining());
//
//        trainingService.save(training);
//        return  new ResponseEntity<TrainingDTO>(new TrainingDTO(training), HttpStatus.CREATED);
//    }
//
//    @DeleteMapping(value = "/{id}")
//    public ResponseEntity<Void> deleteReservation(@PathVariable("id") Long id){
//        Training training = trainingService.findOne(id);
//        if(training == null){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        trainingService.delete(training);
//        return new ResponseEntity<Void>(HttpStatus.OK);
//    }
//}
