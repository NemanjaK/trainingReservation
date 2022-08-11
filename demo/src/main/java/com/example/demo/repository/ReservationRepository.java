package com.example.demo.repository;

import com.example.demo.model.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Page<Reservation> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM reservation  WHERE user_id = :id", nativeQuery = true)
    List<Reservation> findAllById(Long id);
}
