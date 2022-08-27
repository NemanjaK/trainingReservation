package com.example.demo.repository;

import com.example.demo.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

    Training findTrainingByDateOfTraining(LocalDate dateOfTraining);

    List<Training> findAllByDateOfTraining(LocalDate dateOfTraining);
}
