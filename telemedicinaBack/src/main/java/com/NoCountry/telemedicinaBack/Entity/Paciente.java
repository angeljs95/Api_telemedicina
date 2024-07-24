package com.NoCountry.telemedicinaBack.Entity;

import com.NoCountry.telemedicinaBack.Enum.Genero;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
//@AllArgsConstructor
//@NoArgsConstructor
@ToString
//@PrimaryKeyJoinColumn(referencedColumnName ="User")
public class Paciente extends User{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long idPaciente;
    @Enumerated(EnumType.STRING)
    private Genero genero;

    private LocalDate fechaDeNacimiento;
    private String alergias;
   // private  String historialMedico;
    private Long contacto_emergencia;

  //  ----------Relaciones------------
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Consulta> consultas= new ArrayList<>();

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prescripcion> prescripciones;

    @OneToOne(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private HistorialMedico historialMedico;


    // endPoint de consulta
    // listado de doctores poder generar la consulta
    // sacar prescripcion del paciente

    public Paciente(User user){
        this.setId(user.getId());
        this.setNombre(user.getNombre());
        this.setApellido(user.getApellido());
        this.setDocumento(user.getDocumento());
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setPais(user.getPais());
        this.setProvincia(user.getProvincia());
        this.setLocalidad(user.getLocalidad());
        this.setTelefono(user.getTelefono());
        this.setRole(user.getRole());
        this.setFecha_Registro(user.getFecha_Registro());
        this.setEstado(user.isEstado());
    }

    public Paciente(){

    }

}
