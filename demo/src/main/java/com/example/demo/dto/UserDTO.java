package com.example.demo.dto;

import com.example.demo.enums.Role;
import com.example.demo.enums.TrainingType;
import com.example.demo.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class UserDTO {

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

    public UserDTO() {
    }

    public UserDTO(long id, String name, String lastName, String password, String email, String phoneNumber, Role role, TrainingType trainingType,LocalDate membershipExpirationDate) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.trainingType = trainingType;
        this.membershipExpirationDate = membershipExpirationDate;
    }

    public UserDTO(User k) {
        this(k.getId(),k.getName(),k.getLastName(),k.getPassword(),k.getEmail(),k.getPhoneNumber(), k.getRole(), k.getTrainingType(), k.getMembershipExpirationDate());
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

    public TrainingType getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(TrainingType trainingType) {
        this.trainingType = trainingType;
    }

    public LocalDate getMembershipExpirationDate() {
        return membershipExpirationDate;
    }

    public void setMembershipExpirationDate(LocalDate membershipExpirationDate) {
        this.membershipExpirationDate = membershipExpirationDate;
    }
}
