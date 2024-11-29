package com.nicholasescalante.evaluacionjava.services;

import com.nicholasescalante.evaluacionjava.entities.Atencion;

import java.util.List;
import java.util.Optional;

public interface AtencionService {

    List<Atencion> findAll();
    Optional<Atencion> findById(Integer id);
    Atencion save(Atencion atencion);
    void deleteById(Integer id);

}
