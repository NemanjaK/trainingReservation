package com.example.demo.dto;

import com.example.demo.enums.TrainingType;
import com.example.demo.model.Term;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


public class TermDTO {

    private long id;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime time;
    private int occupancy;
    private TrainingType typeOfTraining;


    public TermDTO(long id, LocalDateTime time, int occupancy) {
        this.id = id;
        this.time = time;
        this.occupancy = occupancy;
    }

    public TermDTO(long id, LocalDateTime time, int occupancy, TrainingType typeOfTraining) {
        this.id = id;
        this.time = time;
        this.occupancy = occupancy;
        this.typeOfTraining = typeOfTraining;
    }

    public TermDTO() {
    }

    public TermDTO(Term t) {
        this(t.getId(),t.getTime(),t.getOccupancy(),t.getTypeOfTraining());
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }

    public TrainingType getTraining() {
        return typeOfTraining;
    }

    public void setTraining(TrainingType typeOfTraining ) {
        this.typeOfTraining = typeOfTraining;
    }
}
