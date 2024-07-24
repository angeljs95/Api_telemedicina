package com.NoCountry.telemedicinaBack.Entity;

import com.NoCountry.telemedicinaBack.Enum.EstadoDeConsulta;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    private String idPaciente;
//    private String idMedico;

    private String motivoConsulta;

    private LocalDateTime fechaConsulta;

    @Enumerated(EnumType.STRING)
    private EstadoDeConsulta estado;

    //-------------------------Releaciones-------------------------

    @ManyToOne
    @JoinColumn(name = "horario_id")
    private HorarioDeAtencion horarioAtencion;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

   @OneToOne (mappedBy = "consulta", cascade = CascadeType.ALL, orphanRemoval = true)
    private HojaClinica hojaClinica;


}
