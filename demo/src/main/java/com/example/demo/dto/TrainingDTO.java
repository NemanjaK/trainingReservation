package com.example.demo.dto;

import com.example.demo.enums.TrainingType;
import com.example.demo.model.Training;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class TrainingDTO {

    private Long id;
    private String description;
    private TrainingType typeOfTraining;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfTraining;

    public TrainingDTO() {
    }

    public TrainingDTO(Long id, String description,TrainingType typeOfTraining,LocalDate dateOfTraining) {
        this.id = id;
        this.description = description;
        this.typeOfTraining = typeOfTraining;
        this.dateOfTraining = dateOfTraining;
    }

    public TrainingDTO(Training t) {
        this(t.getId(),t.getDescription(),t.getTypeOfTraining(), t.getDateOfTraining());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TrainingType getTypeOfTraining() {
        return typeOfTraining;
    }

    public void setTypeOfTraining(TrainingType typeOfTraining) {
        this.typeOfTraining = typeOfTraining;
    }

    public LocalDate getDateOfTraining() {
        return dateOfTraining;
    }

    public void setDateOfTraining(LocalDate dateOfTraining) {
        this.dateOfTraining = dateOfTraining;
    }
}

