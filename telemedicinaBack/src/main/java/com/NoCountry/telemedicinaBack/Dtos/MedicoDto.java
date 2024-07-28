package com.NoCountry.telemedicinaBack.Dtos;

import com.NoCountry.telemedicinaBack.Enum.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicoDto {

    private Long id;
    private String nombre;
    private String apellido;
    private Long documento;
    private String pais;
    private String localidad;
    private String provincia;
    private Long telefono;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDate fecha_Registro;
    private boolean estado;

    private String especialidad;
    private String n_licencia;
    private int anios_experiencia;
    private Long num_contacto;
    private String consultorio;
    private String slug;


}
