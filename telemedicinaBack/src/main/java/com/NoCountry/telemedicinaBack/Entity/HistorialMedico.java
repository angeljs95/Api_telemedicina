package com.NoCountry.telemedicinaBack.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class HistorialMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //private String idPaciente;
    private String DiagnosticosPrevios;
    private String tratamientosAnteriores;
    private String notasDelMedico;
   // private LocalDateTime fechaDeConsulta;
    private String comentarios;


    //----------Relaciones------------
//    @OneToOne
//    private Consulta consulta;
//
//    @OneToOne
//    @JoinColumn(name = "paciente_id")
//   private Paciente paciente;
//
//    @OneToMany(mappedBy = "historialMedico", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<HojaClinica> hojasClinicas = new ArrayList<>();
//
//    @OneToMany(mappedBy = "historialMedico", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Archivo> archivos = new ArrayList<>();

}
