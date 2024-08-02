package com.NoCountry.telemedicinaBack.Dtos;

import com.NoCountry.telemedicinaBack.Enum.EstadoDeConsulta;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    private Long idHojaClinica;
 @Enumerated(EnumType.STRING)
 private EstadoDeConsulta estado;
}
