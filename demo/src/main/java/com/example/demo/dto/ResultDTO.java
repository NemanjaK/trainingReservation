package com.example.demo.dto;
import com.example.demo.model.Result;
import com.example.demo.model.Training;
import com.example.demo.model.User;

public class ResultDTO {

    private Long id;
    private String time;
    private String numberOfRunds;
    private User user;
    private Training training;

    public ResultDTO(Long id, String time, String numberOfRunds, User user, Training training) {
        this.id = id;
        this.time = time;
        this.numberOfRunds = numberOfRunds;
        this.user = user;
        this.training = training;
    }

    public ResultDTO(Result r) {
        this(r.getId(),r.getTime(), r.getNumbersOfrounds(),r.getUser(),r.getTraining());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNumberOfRunds() {
        return numberOfRunds;
    }

    public void setNumberOfRunds(String numberOfRunds) {
        this.numberOfRunds = numberOfRunds;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }
}
