package com.example.demo.repository;

import com.example.demo.model.Trening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreningRepository extends JpaRepository<Trening, Long> {
}
