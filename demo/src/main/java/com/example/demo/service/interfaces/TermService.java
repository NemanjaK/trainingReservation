package com.example.demo.service.interfaces;

import com.example.demo.dto.TermDTO;
import com.example.demo.model.Term;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TermService {
    List<TermDTO> findAll();

    List<TermDTO> findAllByDate(LocalDate time);

    List<TermDTO> findAllByDay(LocalDate time);

    List<TermDTO> getTodayTerms(LocalDate yesterday, LocalDate tomorow);
    TermDTO findOne(Long id);

    Term save(TermDTO termDTO);

    void delete(Long id);
}
