package com.example.demo.model;
import com.example.demo.enums.TrainingType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private TrainingType typeOfTraining;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfTraining;

}
