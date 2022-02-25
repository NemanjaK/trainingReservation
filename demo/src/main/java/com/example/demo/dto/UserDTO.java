package com.example.demo.dto;

import com.example.demo.model.User;

public class UserDTO {

    private long id;
    private String name;
    private String lastName;
    private String password;
    private String email;
    private String phoneNumber;
    private String role;

    public UserDTO() {
    }

    public UserDTO(long id, String name, String lastName, String password, String email, String role, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.role = role;
        this.phoneNumber = phoneNumber;
    }

    public UserDTO(User k) {
        this(k.getId(),k.getName(),k.getLastName(),k.getPassword(),k.getEmail(),k.getPhoneNumber(),k.getRole().toString());
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
