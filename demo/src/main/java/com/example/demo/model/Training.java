package com.example.demo.model;
import com.example.demo.enums.TrainingType;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private TrainingType typeOfTraining;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfTraining;

    public Training() {
    }

    public Training(Long id, String description, TrainingType typeOfTraining, LocalDate dateOfTraining) {
        this.id = id;
        this.description = description;
        this.typeOfTraining = typeOfTraining;
        this.dateOfTraining = dateOfTraining;
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
