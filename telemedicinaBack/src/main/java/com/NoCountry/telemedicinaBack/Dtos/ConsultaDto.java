package com.NoCountry.telemedicinaBack.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaDto {

   // private LocalDateTime horaFecha;
    private Long horarioId;
    private Long idMedico;
    private String motivoConsulta;
    private Long idPaciente;
}
