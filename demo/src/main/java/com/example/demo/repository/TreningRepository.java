package com.example.demo.repository;

import com.example.demo.model.Trening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;

@Repository
public interface TreningRepository extends JpaRepository<Trening, Long> {

    Trening findTreningByDatumTreninga(LocalDate datumTreninga);
}
