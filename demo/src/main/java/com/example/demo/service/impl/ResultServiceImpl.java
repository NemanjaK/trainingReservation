package com.example.demo.service.impl;

import com.example.demo.dto.ResultDTO;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.mapper.ResultMapper;
import com.example.demo.model.Result;
import com.example.demo.model.User;
import com.example.demo.repository.ResultRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.interfaces.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class ResultServiceImpl implements ResultService {

    ResultRepository resultRepository;

    ResultMapper resultMapper;

    UserRepository userRepository;
    @Autowired
    public ResultServiceImpl(ResultRepository resultRepository, ResultMapper resultMapper){
        this.resultRepository = resultRepository;
        this.resultMapper = resultMapper;
    }

    @Override
    public List<Result> findAll() {
        return resultRepository.findAll();
    }

    @Override
    public ResultDTO findOne(Long id) {
        return resultRepository.findById(id)
                .map(resultMapper::convertToDto)
                .orElseThrow(() -> new NotFoundException("Result with " + id + " not  found!"));
    }

    @Override
    public Result save(ResultDTO resultDTO, Principal principal) {
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new NotFoundException("User not found"));
        resultDTO.setUser(user);
        return  resultRepository.save(resultMapper.convertToEntity(resultDTO));
    }

    @Override
    public void delete(Result result) {
        resultRepository.delete(result);
    }
}
