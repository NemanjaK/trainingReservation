package com.example.demo.service.impl;

import com.example.demo.model.Termin;
import com.example.demo.repository.TerminRepository;
import com.example.demo.service.interfaces.TerminServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TerminService implements TerminServiceInterface {

    @Autowired
    TerminRepository terminRepository;

    @Override
    public List<Termin> findAll() {
        return terminRepository.findAll();
    }

    @Override
    public Termin findOne(Long id) {
        return terminRepository.getById(id);
    }

    @Override
    public Termin save(Termin termin) {
        return terminRepository.save(termin);
    }

    @Override
    public void delete(Termin termin) {
        terminRepository.delete(termin);
    }
}
