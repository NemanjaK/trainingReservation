package com.example.demo.service.impl;

import com.example.demo.model.Term;
import com.example.demo.repository.TermRepository;
import com.example.demo.service.interfaces.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TermServiceImpl implements TermService {

    @Autowired
    TermRepository termRepository;

    @Override
    public List<Term> findAll() {
        return termRepository.findAll();
    }

    @Override
    public List<Term> findAllByDate(LocalDate time) {
        return termRepository.findAllByTime(time);
    }

    @Override
    public List<Term> findAllByDay(LocalDate time) {
        return termRepository.findAllByDay(time);
    }

    @Override
    public List<Term> findAllWithDates(LocalDate yesterday, LocalDate tomorow) {
        return termRepository.findAllWithDates(yesterday, tomorow);
    }

    @Override
    public Optional<Term> findOne(Long id) {
        return termRepository.findById(id);
    }

    @Override
    public Term findById(Long id) {
        return termRepository.getById(id);
    }

    @Override
    public Term save(Term term) {
        return termRepository.save(term);
    }

    @Override
    public void delete(Term term) {
        termRepository.delete(term);
    }
}
