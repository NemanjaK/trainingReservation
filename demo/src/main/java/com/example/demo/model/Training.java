package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String typeOfTraining;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfTraining;
    @JsonIgnore
    @OneToMany(mappedBy = "training")
    private List<Result> results;
    @JsonIgnore
    @OneToMany(mappedBy = "training")
    private List<Term> terms;

    public Training() {
    }

    public Training(Long id, String description, String typeOfTraining, LocalDate dateOfTraining, List<Result> results, List<Term> terms) {
        this.id = id;
        this.description = description;
        this.typeOfTraining = typeOfTraining;
        this.dateOfTraining = dateOfTraining;
        this.results = results;
        this.terms = terms;
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

    public String getTypeOfTraining() {
        return typeOfTraining;
    }

    public void setTypeOfTraining(String typeOfTraining) {
        this.typeOfTraining = typeOfTraining;
    }

    public LocalDate getDateOfTraining() {
        return dateOfTraining;
    }

    public void setDateOfTraining(LocalDate dateOfTraining) {
        this.dateOfTraining = dateOfTraining;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public List<Term> getTerms() {
        return terms;
    }

    public void setTerms(List<Term> terms) {
        this.terms = terms;
    }
}
