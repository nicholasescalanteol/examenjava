package com.nicholasescalante.evaluacionjava.services;

import com.nicholasescalante.evaluacionjava.entities.Barbero;
import com.nicholasescalante.evaluacionjava.repositories.BarberoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BarberoServiceImpl implements BarberoService {

    @Autowired
    private BarberoRepository barberoRepository;

    public List<Barbero> findAll() {
        return barberoRepository.findAll();
    }

    public Optional<Barbero> findById(Integer id) {
        return barberoRepository.findById(id);
    }

    public Barbero save(Barbero barbero) {
        return barberoRepository.save(barbero);
    }

    public void deleteById(Integer id) {
        barberoRepository.deleteById(id);
    }

}
