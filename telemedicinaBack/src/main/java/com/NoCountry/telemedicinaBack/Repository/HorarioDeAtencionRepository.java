package com.NoCountry.telemedicinaBack.Repository;

import com.NoCountry.telemedicinaBack.Entity.HorarioDeAtencion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HorarioDeAtencionRepository extends JpaRepository<HorarioDeAtencion, Long> {

    public List<HorarioDeAtencion> findByMedicoIdAndDisponibleTrue(Long medicoId);

    public List<HorarioDeAtencion> findByMedicoId(Long medicoId);

    public List<HorarioDeAtencion> findByMedicoIdAndDisponibleFalse(Long medicoId);
}
