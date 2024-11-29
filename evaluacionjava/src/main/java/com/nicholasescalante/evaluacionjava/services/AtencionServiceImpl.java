package com.nicholasescalante.evaluacionjava.services;

import com.nicholasescalante.evaluacionjava.entities.Atencion;
import com.nicholasescalante.evaluacionjava.repositories.AtencionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtencionServiceImpl implements AtencionService {

    @Autowired
    private AtencionRepository atencionRepository;

    public List<Atencion> findAll() {
        return atencionRepository.findAll();
    }

    public Optional<Atencion> findById(Integer id) {
        return atencionRepository.findById(id);
    }

    public Atencion save(Atencion atencion) {
        return atencionRepository.save(atencion);
    }

    public void deleteById(Integer id) {
        atencionRepository.deleteById(id);
    }

}
