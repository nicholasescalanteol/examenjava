package com.nicholasescalante.evaluacionjava.services;

import com.nicholasescalante.evaluacionjava.entities.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    List<Cliente> findAll();
    Optional<Cliente> findById(Integer id);
    Cliente save(Cliente cliente);
    void deleteById(Integer id);

}
