package com.example.demo.service.interfaces;

import com.example.demo.dto.ResultDTO;
import com.example.demo.model.Result;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface ResultService {

    List<Result> findAll();

    ResultDTO findOne(Long id);

    Result save(ResultDTO resultDTO, Principal principal);

    void delete(Result result);
}
