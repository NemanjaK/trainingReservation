package com.example.demo.dto;
import com.example.demo.model.Result;
import com.example.demo.model.User;

public class ResultDTO {

    private Long id;
    private String time;
    private String numberOfRunds;
    private User user;

    public ResultDTO(Long id, String time, String numberOfRunds, User user) {
        this.id = id;
        this.time = time;
        this.numberOfRunds = numberOfRunds;
        this.user = user;
    }

    public ResultDTO(Result r) {
        this(r.getId(),r.getTime(), r.getNumbersOfrounds(),r.getUser());
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

}
