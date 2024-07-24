package com.NoCountry.telemedicinaBack.Repository;

import com.NoCountry.telemedicinaBack.Entity.Paciente;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}
