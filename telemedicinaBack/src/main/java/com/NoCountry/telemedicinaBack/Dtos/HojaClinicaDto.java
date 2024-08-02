package com.NoCountry.telemedicinaBack.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HojaClinicaDto {

    private Long id;
    private String notasMedicas;
    private String diagnostico;
    private String tratamiento;
    private String sintomatologia;
    private Long medicoId;
private Long pacienteId;
private Long consultaId;
    private LocalDate fechaConsulta;
}
