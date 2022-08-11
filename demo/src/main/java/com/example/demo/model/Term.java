package com.example.demo.model;

import com.example.demo.enums.TrainingType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

@Entity
public class Term {
    @Id
    @Column(unique=true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime time;
    private int occupancy;
    private TrainingType typeOfTraining;
    @JsonIgnore
    @OneToMany(mappedBy = "term", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    public Term() {
    }

    public Term(long id, LocalDateTime time, int occupancy) {
        this.id = id;
        this.time = time;
        this.occupancy = occupancy;
    }

    public Term(long id, LocalDateTime time, int occupancy, List<Reservation> reservations) {
        this.id = id;
        this.time = time;
        this.occupancy = occupancy;
        this.reservations = reservations;
    }

    public Term(long id, LocalDateTime time, int occupancy, TrainingType typeOfTraining, List<Reservation> reservations) {
        this.id = id;
        this.time = time;
        this.occupancy = occupancy;
        this.typeOfTraining = typeOfTraining;
        this.reservations = reservations;
    }

    public TrainingType getTypeOfTraining() {
        return typeOfTraining;
    }

    public void setTypeOfTraining(TrainingType typeOfTraining) {
        this.typeOfTraining = typeOfTraining;
    }

    public long  getId() {
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

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
