package com.example.demo.repository;

import com.example.demo.model.Rezultat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RezultatRepository extends JpaRepository<Rezultat, Long> {
}
