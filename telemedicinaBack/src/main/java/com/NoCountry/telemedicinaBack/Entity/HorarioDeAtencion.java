package com.NoCountry.telemedicinaBack.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.BindingPriority;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HorarioDeAtencion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime inicio;
    private LocalDateTime fin;
    private boolean disponible;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

//    public HorarioDeAtencion(LocalDateTime inicio, LocalDateTime fin) {
//        this.inicio = inicio;
//        this.fin = fin;
//    }
//
//    public boolean seSuperponeCon(HorarioDeAtencion horario) {
//        return (inicio.isBefore(horario.fin) && fin.isAfter(horario.inicio));
//    }
}
