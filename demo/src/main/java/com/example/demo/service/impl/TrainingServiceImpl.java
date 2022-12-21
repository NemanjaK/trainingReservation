package com.example.demo.service.impl;

import com.example.demo.dto.TrainingDTO;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.mapper.TrainingMapper;
import com.example.demo.model.Training;
import com.example.demo.repository.TrainingRepository;
import com.example.demo.service.interfaces.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {

    TrainingRepository trainingRepository;

    TrainingMapper trainingMapper;

    @Autowired
    public TrainingServiceImpl(TrainingRepository trainingRepository, TrainingMapper trainingMapper){
        this.trainingRepository = trainingRepository;
        this.trainingMapper = trainingMapper;
    }


    @Override
    public TrainingDTO findOne(Long id) {
        return trainingRepository.findById(id)
                .map(trainingMapper::convertToDto)
                .orElseThrow(() -> new NotFoundException("Training not found!"));
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
    public TrainingDTO findTrainingByDateOfTraining(LocalDate dateOfTraining) {
        return trainingRepository.findTrainingByDateOfTraining(dateOfTraining)
                .map(trainingMapper::convertToDto)
                .orElseThrow(() -> new NotFoundException("Training by date not found!"));
    }

    @Override
    public List<Training> findAllByDateOfTraining(LocalDate dateOfTraining) {
        return trainingRepository.findAllByDateOfTraining(dateOfTraining);
    }
}
