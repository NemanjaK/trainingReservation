package com.example.demo.service.interfaces;

import com.example.demo.dto.TrainingDTO;
import com.example.demo.model.Training;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TrainingService {

    TrainingDTO findOne(Long id);

    Training save(Training training);

    void delete(Training training);

    TrainingDTO findTrainingByDateOfTraining(LocalDate dateOfTraining);

    List<Training> findAllByDateOfTraining(LocalDate dateOfTraining);
}
