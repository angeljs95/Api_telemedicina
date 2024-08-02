package com.NoCountry.telemedicinaBack.Services;

import com.NoCountry.telemedicinaBack.Dtos.HorariosDto;
import com.NoCountry.telemedicinaBack.Entity.HorarioDeAtencion;
import com.NoCountry.telemedicinaBack.Entity.Medico;
import com.NoCountry.telemedicinaBack.Entity.User;
import com.NoCountry.telemedicinaBack.Repository.HorarioDeAtencionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HorarioDeAtencionService {

    @Autowired
    private HorarioDeAtencionRepository horarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<HorarioDeAtencion> obtenerHorariosDisponiblesPorMedico(Long medicoId) {
        return horarioRepository.findByMedicoIdAndDisponibleTrue(medicoId);
    }

    public List<HorarioDeAtencion> obtenerTodosLosHorariosPorMedico(Long medicoId){
     return horarioRepository.findByMedicoId(medicoId);
    }


    public Boolean guardarHorarioAtencion(HorarioDeAtencion horarioAtencion) {

        List<HorarioDeAtencion> horariosMedico = horarioRepository.findByMedicoId(horarioAtencion.getMedico().getId());
// Validamos que el horario que esta tratando de ingresar este ocupado, sino registra el nuevo horario
        for( int i=0; i<horariosMedico.size();  i++){
      if( horarioAtencion.getInicio().isBefore(horariosMedico.get(i).getFin())){
          if(horarioAtencion.getFin().isAfter(horariosMedico.get(i).getInicio())){
              System.out.println("El horario se encuentra registrado");
              return false;
          }
      }
        }
                HorarioDeAtencion nuevoHorario = HorarioDeAtencion.builder()
                        .inicio(horarioAtencion.getInicio())
                        .fin(horarioAtencion.getFin())
                        .disponible(true)
                        .medico(horarioAtencion.getMedico())
                        .build();
                horarioRepository.save(nuevoHorario);
                return true;}

    public void habilitarHorarios(Long idHorario){
        HorarioDeAtencion horario= horarioRepository.findById(idHorario).orElse(null);
        if(!horario.isDisponible()) {
            horario.setDisponible(true);
        }
        horarioRepository.save(horario);
    }

    public void deshabilitarHorario(Long id){
        HorarioDeAtencion horario= horarioRepository.findById(id).orElse(null);
        if(horario.isDisponible()){
            horario.setDisponible(false);
        }
        horarioRepository.save(horario);
    }

    public void eliminarHorario(Long idHorario){
        horarioRepository.deleteById(idHorario);

    }

}