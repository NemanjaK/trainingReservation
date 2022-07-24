package com.example.demo.service.interfaces;

import com.example.demo.model.Term;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface TermService {
    List<Term> findAll();

    List<Term> findAllByDate(LocalDate time);

    List<Term> findAllWithDates(LocalDate yesterday, LocalDate tomorow);
    Term findOne(Long id);

    Term save(Term term);

    void delete(Term term);
}
