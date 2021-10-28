package com.example.demo.repository;

import com.example.demo.model.Rezervacija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RezervacijaRepository extends JpaRepository<Rezervacija, Long> {

}
