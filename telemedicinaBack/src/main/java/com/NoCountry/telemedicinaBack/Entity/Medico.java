package com.NoCountry.telemedicinaBack.Entity;

import com.NoCountry.telemedicinaBack.Enum.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
//@AllArgsConstructor
@NoArgsConstructor
//@PrimaryKeyJoinColumn(referencedColumnName = "User")
public class Medico extends User{

// @Id
// @GeneratedValue(strategy = GenerationType.IDENTITY)
// private long idMedico;

 private String especialidad;
 private String n_licencia;
 private int anios_experiencia;
 private Integer num_contacto;
 private String consultorio;

 @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, orphanRemoval = true)
 private List<Consulta> consultas= new ArrayList<>();

 @OneToMany(mappedBy = "medico")//, cascade = CascadeType.ALL, orphanRemoval = true)
 private List<Prescripcion> prescripciones;

 @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, orphanRemoval = true)
 private List<HorarioDeAtencion> horariosDeAtencion;


 public Medico(User user){
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

}
