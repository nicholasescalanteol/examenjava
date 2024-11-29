package com.nicholasescalante.evaluacionjava.controllers;

import com.nicholasescalante.evaluacionjava.entities.DTOs.ClienteDTO;
import com.nicholasescalante.evaluacionjava.entities.Cliente;
import com.nicholasescalante.evaluacionjava.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
@Validated
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/")
    public List<ClienteDTO> getAllClientes() {
        return clienteService.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable Integer id) {
        Optional<Cliente> cliente = clienteService.findById(id);
        return cliente.map(value -> ResponseEntity.ok(convertToDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ClienteDTO createCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = convertToEntity(clienteDTO);
        return convertToDto(clienteService.save(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Integer id, @Valid @RequestBody ClienteDTO clienteDTO) {
        Optional<Cliente> cliente = clienteService.findById(id);
        if (cliente.isPresent()) {
            Cliente updatedCliente = convertToEntity(clienteDTO);
            updatedCliente.setId(id);
            return ResponseEntity.ok(convertToDto(clienteService.save(updatedCliente)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer id) {
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private ClienteDTO convertToDto(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNombres(cliente.getNombres());
        clienteDTO.setApellidos(cliente.getApellidos());
        clienteDTO.setDni(cliente.getDni());
        clienteDTO.setEdad(cliente.getEdad());
        return clienteDTO;
    }

    private Cliente convertToEntity(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNombres(clienteDTO.getNombres());
        cliente.setApellidos(clienteDTO.getApellidos());
        cliente.setDni(clienteDTO.getDni());
        cliente.setEdad(clienteDTO.getEdad());
        return cliente;
    }
}
