package com.example.demo.model;

import com.example.demo.enums.TrainingType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

}
