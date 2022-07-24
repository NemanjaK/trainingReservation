package com.example.demo.service.interfaces;

import com.example.demo.model.Result;

import java.util.List;

public interface ResultService {

    List<Result> findAll();

    Result findOne(Long id);

    Result save(Result result);

    void delete(Result result);
}
