package com.NoCountry.telemedicinaBack.Dtos;

import com.NoCountry.telemedicinaBack.Enum.Genero;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActualizarDto {

    //------------ para medico----------
    private String especialidad;
    private String n_licencia;
    private int anios_experiencia;
    private Integer num_contacto;
    private String consultorio;

    //--------para paciente-------------

    @Enumerated(EnumType.STRING)
    private Genero genero;
    private LocalDate fechaDeNacimiento;
    private String alergias;
    // private  String historialMedico;
    private String provincia;
    private String localidad;
    private Long n_contacto;

}
