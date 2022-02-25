package com.example.demo.dto;

import com.example.demo.model.Term;
import com.example.demo.model.Training;

import java.util.Date;

public class TermDTO {

    private long id;
    private Date time;
    private int occupancy;
    private Training training;

    public TermDTO(long id, Date time, int occupancy, Training training) {
        this.id = id;
        this.time = time;
        this.occupancy = occupancy;
        this.training = training;
    }

    public TermDTO(Term t) {
        this(t.getId(),t.getTime(),t.getOccupancy(),t.getTraining());
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }
}
