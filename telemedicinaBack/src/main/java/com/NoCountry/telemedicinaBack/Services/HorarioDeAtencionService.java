package com.NoCountry.telemedicinaBack.Services;

import com.NoCountry.telemedicinaBack.Entity.HorarioDeAtencion;
import com.NoCountry.telemedicinaBack.Repository.HorarioDeAtencionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorarioDeAtencionService {

    @Autowired
    private HorarioDeAtencionRepository horarioRepository;

    public List<HorarioDeAtencion> obtenerHorariosDisponiblesPorMedico(Long medicoId) {
        return horarioRepository.findByMedicoIdAndDisponibleTrue(medicoId);
    }

    public List<HorarioDeAtencion> obtenerTodosLosHorariosPorMedico(Long medicoId){
     return horarioRepository.findByMedicoId(medicoId);
    }

    public HorarioDeAtencion guardarHorarioAtencion(HorarioDeAtencion horarioAtencion) {
        return horarioRepository.save(horarioAtencion);
    }

    public void habilitarHorarios(Long idHorario){
        HorarioDeAtencion horario= horarioRepository.findById(idHorario).orElse(null);
        if(!horario.isDisponible()) {
            horario.setDisponible(true);
        }
        horarioRepository.save(horario);
    }

    public void eliminarHorario(Long idHorario){
        horarioRepository.deleteById(idHorario);

    }

}