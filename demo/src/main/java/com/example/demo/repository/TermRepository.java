package com.example.demo.repository;

import com.example.demo.dto.TermDTO;
import com.example.demo.model.Term;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TermRepository extends JpaRepository<Term, Long> {

    List<Term> findAllByTime(LocalDate time);

    @Query(value = "SELECT * FROM term  WHERE time between :day and CONCAT(:day, ' 23:59:59');", nativeQuery = true)
    List<Term> findAllByDay(LocalDate day);

   @Query(value = "SELECT * FROM term  WHERE time > :yestarday AND time < :tomorow", nativeQuery = true)
    List<Term> findAllWithDates(LocalDate yestarday, LocalDate tomorow);
}
