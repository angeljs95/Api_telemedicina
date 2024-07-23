package com.NoCountry.telemedicinaBack.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Prescripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    private String idPaciente;
//   private String idMedico;


//   private String dosis;
//    private LocalDate fechaInicio;
//    private LocalDate fechaDeCulminacion;
     private LocalDate fechaEmision;
   //private String indicaciones;

    //----------Relaciones------------

    @ManyToOne
    @JoinColumn(name = "paciente_id")
   private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @OneToOne
    @JoinColumn(name = "hoja_clinica_id")
    private HojaClinica hojaClinica;

    @OneToMany(mappedBy = "prescripcion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Medicamento> medicamentos = new ArrayList<>();

//


}
