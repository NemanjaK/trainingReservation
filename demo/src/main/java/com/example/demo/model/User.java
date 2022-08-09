package com.example.demo.model;

import com.example.demo.enums.Role;
import com.example.demo.enums.TrainingType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String lastName;
    private String password;
    private String email;
    private String phoneNumber;
    private Role role;
    private TrainingType trainingType;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate membershipExpirationDate;

    private boolean status;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Result> results;

    @JsonIgnore
    @OneToMany(mappedBy = "term")
    private List<Reservation> reservations;


    public User() {
    }

    public User(long id, String name, String lastName, String password, String email, String phoneNumber,
                Role role, List<Result> results, TrainingType trainingType, boolean status, LocalDate membershipExpirationDate) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.results = results;
        this.trainingType = trainingType;
        this.status = status;
        this.membershipExpirationDate = membershipExpirationDate;
    }

    public User(long id, String name, String lastName, String password, String email, String phoneNumber, Role role, TrainingType trainingType, LocalDate membershipExpirationDate, boolean status, List<Result> results, List<Reservation> reservations) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.trainingType = trainingType;
        this.membershipExpirationDate = membershipExpirationDate;
        this.status = status;
        this.results = results;
        this.reservations = reservations;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public TrainingType getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(TrainingType trainingType) {
        this.trainingType = trainingType;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDate getMembershipExpirationDate() {
        return membershipExpirationDate;
    }

    public void setMembershipExpirationDate(LocalDate membershipExpirationDate) {
        this.membershipExpirationDate = membershipExpirationDate;
    }

    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roles = new ArrayList<>();
        roles.add(this.role);
        return roles;
    }
}
