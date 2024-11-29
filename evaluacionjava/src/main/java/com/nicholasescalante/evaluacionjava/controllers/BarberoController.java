package com.nicholasescalante.evaluacionjava.controllers;

import com.nicholasescalante.evaluacionjava.entities.DTOs.BarberoDTO;
import com.nicholasescalante.evaluacionjava.entities.Barbero;
import com.nicholasescalante.evaluacionjava.services.BarberoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/barberos")
@Validated
public class BarberoController {
    @Autowired
    private BarberoService barberoService;

    @GetMapping("/")
    public List<BarberoDTO> getAllBarberos() {
        return barberoService.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BarberoDTO> getBarberoById(@PathVariable Integer id) {
        Optional<Barbero> barbero = barberoService.findById(id);
        return barbero.map(value -> ResponseEntity.ok(convertToDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public BarberoDTO createBarbero(@Valid @RequestBody BarberoDTO barberoDTO) {
        Barbero barbero = convertToEntity(barberoDTO);
        return convertToDto(barberoService.save(barbero));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BarberoDTO> updateBarbero(@PathVariable Integer id, @Valid @RequestBody BarberoDTO barberoDTO) {
        Optional<Barbero> barbero = barberoService.findById(id);
        if (barbero.isPresent()) {
            Barbero updatedBarbero = convertToEntity(barberoDTO);
            updatedBarbero.setId(id);
            return ResponseEntity.ok(convertToDto(barberoService.save(updatedBarbero)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBarbero(@PathVariable Integer id) {
        barberoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private BarberoDTO convertToDto(Barbero barbero) {
        BarberoDTO barberoDTO = new BarberoDTO();
        barberoDTO.setNombre(barbero.getNombre());
        barberoDTO.setApellidos(barbero.getApellidos());
        barberoDTO.setAnosExperiencia(barbero.getAnosExperiencia());
        barberoDTO.setEstado(barbero.isEstado());
        return barberoDTO;
    }

    private Barbero convertToEntity(BarberoDTO barberoDTO) {
        Barbero barbero = new Barbero();
        barbero.setNombre(barberoDTO.getNombre());
        barbero.setApellidos(barberoDTO.getApellidos());
        barbero.setAnosExperiencia(barberoDTO.getAnosExperiencia());
        barbero.setEstado(barberoDTO.getEstado());
        return barbero;
    }
}
