package com.nicholasescalante.evaluacionjava.repositories;

import com.nicholasescalante.evaluacionjava.entities.Barbero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarberoRepository extends JpaRepository<Barbero, Integer> {
}
