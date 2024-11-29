package com.nicholasescalante.evaluacionjava.controllers;

import com.nicholasescalante.evaluacionjava.entities.DTOs.AtencionDTO;
import com.nicholasescalante.evaluacionjava.entities.Atencion;
import com.nicholasescalante.evaluacionjava.entities.Cliente;
import com.nicholasescalante.evaluacionjava.entities.Barbero;
import com.nicholasescalante.evaluacionjava.services.AtencionService;
import com.nicholasescalante.evaluacionjava.services.ClienteService;
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
@RequestMapping("/atenciones")
@Validated
public class AtencionController {
    @Autowired
    private AtencionService atencionService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private BarberoService barberoService;

    @GetMapping("/")
    public List<AtencionDTO> getAllAtenciones() {
        return atencionService.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtencionDTO> getAtencionById(@PathVariable Integer id) {
        Optional<Atencion> atencion = atencionService.findById(id);
        return atencion.map(value -> ResponseEntity.ok(convertToDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public AtencionDTO createAtencion(@Valid @RequestBody AtencionDTO atencionDTO) {
        Atencion atencion = convertToEntity(atencionDTO);
        return convertToDto(atencionService.save(atencion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtencionDTO> updateAtencion(@PathVariable Integer id, @Valid @RequestBody AtencionDTO atencionDTO) {
        Optional<Atencion> atencion = atencionService.findById(id);
        if (atencion.isPresent()) {
            Atencion updatedAtencion = convertToEntity(atencionDTO);
            updatedAtencion.setId(id);
            return ResponseEntity.ok(convertToDto(atencionService.save(updatedAtencion)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAtencion(@PathVariable Integer id) {
        atencionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private AtencionDTO convertToDto(Atencion atencion) {
        AtencionDTO atencionDTO = new AtencionDTO();
        atencionDTO.setFechaAtencion(atencion.getFechaAtencion());
        atencionDTO.setClienteId(atencion.getCliente().getId());
        atencionDTO.setBarberoId(atencion.getBarbero().getId());
        atencionDTO.setTipoCorte(atencion.getTipoCorte());
        atencionDTO.setEstado(atencion.isEstado());
        atencionDTO.setNombreCliente(atencion.getCliente().getNombres());
        atencionDTO.setNombreBarbero(atencion.getBarbero().getNombre());
        return atencionDTO;
    }

    private Atencion convertToEntity(AtencionDTO atencionDTO) {
        Atencion atencion = new Atencion();
        atencion.setFechaAtencion(atencionDTO.getFechaAtencion());
        atencion.setTipoCorte(atencionDTO.getTipoCorte());
        atencion.setEstado(atencionDTO.getEstado());

        Optional<Cliente> cliente = clienteService.findById(atencionDTO.getClienteId());
        cliente.ifPresent(atencion::setCliente);

        Optional<Barbero> barbero = barberoService.findById(atencionDTO.getBarberoId());
        barbero.ifPresent(atencion::setBarbero);

        return atencion;
    }
}