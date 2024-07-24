package com.NoCountry.telemedicinaBack.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicoDto {
    private Long id;
    private String especialidad;
    private String n_licencia;
    private int anios_experiencia;
    private Integer num_contacto;
    private String consultorio;



}
