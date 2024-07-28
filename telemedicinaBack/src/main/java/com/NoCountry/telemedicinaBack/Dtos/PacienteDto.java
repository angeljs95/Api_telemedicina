package com.NoCountry.telemedicinaBack.Dtos;

import com.NoCountry.telemedicinaBack.Enum.Genero;
import com.NoCountry.telemedicinaBack.Enum.Role;
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
public class PacienteDto {

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


    //-----atributos paciente
    @Enumerated(EnumType.STRING)
    private Genero genero;
    private LocalDate fechaDeNacimiento;
    private String alergias;
    // private  String historialMedico;
    private Long contacto_emergencia;


}
