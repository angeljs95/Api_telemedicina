package com.NoCountry.telemedicinaBack.Repository;

import com.NoCountry.telemedicinaBack.Entity.Consulta;
import com.NoCountry.telemedicinaBack.Entity.HojaClinica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HojaClinicaRepository extends JpaRepository<HojaClinica, Long> {

    public HojaClinica findByConsultaId(Long idConsulta);

    public HojaClinica findByConsulta(Consulta consulta);

    List<HojaClinica> findByPacienteId(Long idPaciente);
}
