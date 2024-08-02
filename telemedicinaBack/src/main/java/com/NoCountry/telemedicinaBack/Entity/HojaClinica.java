package com.NoCountry.telemedicinaBack.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class HojaClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
// detalle de la consukta
    // buscar sintomas basicos para un paciente
    private String notasMedicas;
    private String diagnostico; // sintomas
    private String tratamiento;
    private String sintomatologia;
    // private String recetas;
    private Long medicoId;

    private LocalDate fechaConsulta;

// -------------relaciones----------------

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @OneToOne
    @JoinColumn(name = "consulta_id")
    private Consulta consulta;

    public HojaClinica(Consulta consulta){
        this.setMedicoId(consulta.getMedico().getId());
        this.setConsulta(consulta);
        this.setPaciente(consulta.getPaciente());
        this.setFechaConsulta(LocalDate.from(consulta.getFechaConsulta()));
    }

//    @ManyToOne
//    @JoinColumn(name = "historial_medico_id")
//    private HistorialMedico historialMedico;
//
//
//@OneToOne(mappedBy = "hojaClinica", cascade = CascadeType.ALL)
//private Prescripcion prescripcion;

}
