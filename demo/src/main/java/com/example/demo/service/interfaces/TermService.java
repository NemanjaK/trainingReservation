package com.example.demo.service.interfaces;

import com.example.demo.model.Term;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TermService {
    List<Term> findAll();

    List<Term> findAllByDate(LocalDate time);

    Optional<Term> findById(Long id);

    List<Term> findAllByDay(LocalDate time);

    List<Term> findAllWithDates(LocalDate yesterday, LocalDate tomorow);
    Optional<Term> findOne(Long id);

    Term save(Term term);

    void delete(Term term);
}
