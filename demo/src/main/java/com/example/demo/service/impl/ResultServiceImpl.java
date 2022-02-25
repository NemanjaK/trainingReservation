package com.example.demo.service.impl;

import com.example.demo.model.Result;
import com.example.demo.repository.ResultRepository;
import com.example.demo.service.interfaces.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    ResultRepository resultRepository;

    @Override
    public List<Result> findAll() {
        return resultRepository.findAll();
    }

    @Override
    public Result findOne(Long id) {
        return resultRepository.getById(id);
    }

    @Override
    public Result save(Result result) {
        return  resultRepository.save(result);
    }

    @Override
    public void delete(Result result) {
        resultRepository.delete(result);
    }
}
