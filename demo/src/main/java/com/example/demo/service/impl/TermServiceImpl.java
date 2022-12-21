package com.example.demo.service.impl;

import com.example.demo.dto.TermDTO;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.mapper.TermMapper;
import com.example.demo.mapper.TrainingMapper;
import com.example.demo.model.Term;
import com.example.demo.repository.TermRepository;
import com.example.demo.service.interfaces.TermService;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TermServiceImpl implements TermService {


    TermRepository termRepository;

    TermMapper termMapper;

    @Autowired
    public TermServiceImpl(TermRepository termRepository, TermMapper termMapper){
        this.termRepository = termRepository;
        this.termMapper = termMapper;
    }

    @Override
    public List<TermDTO> findAll() {
        return termRepository.findAll()
                 .stream()
                 .map(termMapper::convertToDto)
                 .collect(Collectors.toList());
    }

    @Override
    public List<TermDTO> findAllByDate(LocalDate time) {
        return termRepository.findAllByDay(time)
                .stream()
                .map(termMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TermDTO> findAllByDay(LocalDate time) {
        return termRepository.findAllByDay(time)
                .stream()
                .map(termMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TermDTO> getTodayTerms(LocalDate yesterday, LocalDate tomorow) {
        return termRepository.findAllWithDates(yesterday, tomorow)
                .stream()
                .map(termMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TermDTO findOne(Long id) {
       return termRepository.findById(id)
                .map(termMapper::convertToDto)
                .orElseThrow(() -> new NotFoundException("Term not found!"));
    }

    @Override
    public Term save(TermDTO termDTO) {
        return termRepository.save(termMapper.convertToEntity(termDTO));
    }

    @Override
    public void delete(Long id) {
        Term term = termRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Term not found!"));
        termRepository.delete(term);
    }
}
