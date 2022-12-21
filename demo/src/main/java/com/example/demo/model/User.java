package com.example.demo.model;

import com.example.demo.enums.Role;
import com.example.demo.enums.TrainingType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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


    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roles = new ArrayList<>();
        roles.add(this.role);
        return roles;
    }
}
