package com.example.demo.service.interfaces;

import com.example.demo.model.Result;

import java.util.List;
import java.util.Optional;

public interface ResultService {

    List<Result> findAll();

    Optional<Result> findOne(Long id);

    Result save(Result result);

    void delete(Result result);
}
