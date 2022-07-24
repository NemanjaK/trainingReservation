package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

@Entity
public class Term {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate time;
    private int occupancy;
    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private Training training;
    @JsonIgnore
    @OneToMany(mappedBy = "term")
    private List<Reservation> reservations;

    public Term() {
    }

    public Term(long id, LocalDate time, int occupancy) {
        this.id = id;
        this.time = time;
        this.occupancy = occupancy;
    }

    public Term(long id, LocalDate time, int occupancy, Training training, List<Reservation> reservations) {
        this.id = id;
        this.time = time;
        this.occupancy = occupancy;
        this.training = training;
        this.reservations = reservations;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
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

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
