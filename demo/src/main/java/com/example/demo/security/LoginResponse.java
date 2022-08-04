package com.example.demo.security;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.juli.logging.Log;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;

public class LoginResponse {

    private String token;
    private String name;
    private String role;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate membershipExpirationDate;

    public LoginResponse() {
    }


    public LoginResponse(String token, String name, String role, LocalDate membershipExpirationDate) {

        this.token = token;
        this.name = name;
        this.role = role;
        this.membershipExpirationDate = membershipExpirationDate;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getMembershipExpirationDate() {
        return membershipExpirationDate;
    }

    public void setMembershipExpirationDate(LocalDate membershipExpirationDate) {
        this.membershipExpirationDate = membershipExpirationDate;
    }
}