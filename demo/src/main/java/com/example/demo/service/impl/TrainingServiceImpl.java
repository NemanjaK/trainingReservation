package com.example.demo.service.impl;

import com.example.demo.model.Training;
import com.example.demo.repository.TrainingRepository;
import com.example.demo.service.interfaces.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    TrainingRepository trainingRepository;

    @Override
    public Optional<Training> findOne(Long id) {
        return trainingRepository.findById(id);
    }

    @Override
    public Training save(Training trening) {
        return trainingRepository.save(trening);
    }

    @Override
    public void delete(Training trening) {
        trainingRepository.delete(trening);
    }

    @Override
    public Optional<Training> findTrainingByDateOfTraining(LocalDate dateOfTraining) {
        return trainingRepository.findTrainingByDateOfTraining(dateOfTraining);
    }

    @Override
    public List<Training> findAllByDateOfTraining(LocalDate dateOfTraining) {
        return trainingRepository.findAllByDateOfTraining(dateOfTraining);
    }
}
