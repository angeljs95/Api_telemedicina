package com.NoCountry.telemedicinaBack.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

}
