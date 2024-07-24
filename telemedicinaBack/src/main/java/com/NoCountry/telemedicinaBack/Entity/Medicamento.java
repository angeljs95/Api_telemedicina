package com.NoCountry.telemedicinaBack.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medicamento {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    // Pienso en colocarlo en prescripcion en una lista, asi se pueden agregar varios de ser necesario

    private String nombre;
    private String miligramos;
    private String  indicaciones;
    private LocalDate fechaInicio;
    private LocalDate fechaDeCulminacion;

    // ---------------Relaciones-----------------

    @ManyToOne
    @JoinColumn(name="prescripcion_id")
    private Prescripcion prescripcion;

}
