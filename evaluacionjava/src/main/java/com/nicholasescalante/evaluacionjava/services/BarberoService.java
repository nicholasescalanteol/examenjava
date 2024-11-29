package com.nicholasescalante.evaluacionjava.services;

import com.nicholasescalante.evaluacionjava.entities.Barbero;

import java.util.List;
import java.util.Optional;

public interface BarberoService {

    List<Barbero> findAll();
    Optional<Barbero> findById(Integer id);
    Barbero save(Barbero barbero);
    void deleteById(Integer id);

}
