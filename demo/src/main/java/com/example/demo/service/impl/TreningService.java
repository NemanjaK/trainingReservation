package com.example.demo.service.impl;

import com.example.demo.model.Trening;
import com.example.demo.repository.TreningRepository;
import com.example.demo.service.interfaces.TreningServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreningService implements TreningServiceInterface {

    @Autowired
    TreningRepository treningRepository;

    @Override
    public List<Trening> findAll() {
        return treningRepository.findAll();
    }

    @Override
    public Trening findOne(Long id) {
        return treningRepository.getById(id);
    }

    @Override
    public Trening save(Trening trening) {
        return treningRepository.save(trening);
    }

    @Override
    public void delete(Trening trening) {
        treningRepository.delete(trening);
    }
}
