package com.nicholasescalante.evaluacionjava.entities.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class AtencionDTO {
    @NotNull
    private LocalDateTime fechaAtencion;

    @NotNull
    private Integer clienteId;
    private String nombreCliente;

    @NotNull
    private Integer barberoId;
    private String nombreBarbero;

    @NotBlank
    private String tipoCorte;

    @NotNull
    private Boolean estado;

}
