package com.NoCountry.telemedicinaBack.Entity;

import jakarta.persistence.*;
import lombok.*;

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
    // private String recetas;

    private Date fechaConsulta;

// -------------relaciones----------------
    @OneToOne
    @JoinColumn(name = "consulta_id")
    private Consulta consulta;

    @ManyToOne
    @JoinColumn(name = "historial_medico_id")
    private HistorialMedico historialMedico;


@OneToOne(mappedBy = "hojaClinica", cascade = CascadeType.ALL)
private Prescripcion prescripcion;

}
