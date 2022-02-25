package com.example.demo.service.interfaces;

import com.example.demo.model.Term;

import java.util.List;

public interface TermService {
    List<Term> findAll();

    Term findOne(Long id);

    Term save(Term term);

    void delete(Term term);
}
