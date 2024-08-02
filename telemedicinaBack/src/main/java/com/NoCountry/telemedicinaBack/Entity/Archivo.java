package com.NoCountry.telemedicinaBack.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Archivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreArchivo;
    private String tipoArchivo;

    @Lob
    private byte[] contenido;

//    @ManyToOne
//    @JoinColumn(name = "historial_medico_id")
//    private HistorialMedico historialMedico;

}
