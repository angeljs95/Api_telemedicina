package com.NoCountry.telemedicinaBack.Services;

import com.NoCountry.telemedicinaBack.Dtos.ConsultaDto;
import com.NoCountry.telemedicinaBack.Entity.*;
import com.NoCountry.telemedicinaBack.Enum.EstadoDeConsulta;
import com.NoCountry.telemedicinaBack.Repository.ConsultaRepository;
import com.NoCountry.telemedicinaBack.Repository.HorarioDeAtencionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConsultaService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private HorarioDeAtencionRepository horarioRepository;

public ConsultaDto verConsulta(Long consultaId){
    Consulta consulta= consultaRepository.findById(consultaId).orElse(null);
    return modelMapper.map(consulta, ConsultaDto.class);
}
public List<ConsultaDto> listarConsultasMedico(Long id) {
    List<Consulta> consultas = consultaRepository.findByMedicoId(id);
    List<ConsultaDto> lista = consultas.stream().map(consulta -> modelMapper.map(consulta, ConsultaDto.class)).collect(Collectors.toList());
    return lista;
}

    public List<ConsultaDto> listarConsultasPaciente(Long id){
        List<Consulta> consultas= consultaRepository.findByPacienteId(id);
        List<ConsultaDto> lista= consultas.stream().map(consulta -> modelMapper.map(consulta, ConsultaDto.class)).collect(Collectors.toList());
        return lista;

    }

    public Consulta agendarCita(ConsultaDto consultaDto, Paciente paciente, Medico medico) {
        Optional<HorarioDeAtencion> respuesta = horarioRepository.findById(consultaDto.getHorarioId());
        if (!respuesta.isPresent() || !respuesta.get().isDisponible()) {
            throw new RuntimeException("Horario no disponible");
        }
        HorarioDeAtencion horario = respuesta.get();
        horario.setDisponible(false);
        horarioRepository.save(horario);

        Consulta consulta = new Consulta();
        consulta.setHorarioAtencion(horario);
        consulta.setMotivoConsulta(consultaDto.getMotivoConsulta());
        consulta.setPaciente(paciente);
        consulta.setFechaConsulta(horario.getInicio());
        consulta.setEstado(EstadoDeConsulta.PROGRAMADA);
        consulta.setMedico(medico);
        consulta.setHojaClinica(new HojaClinica(consulta));
        return consultaRepository.save(consulta);
       // consultaRepository.save(consulta);
    }

    public void cancelarCita(Long idCita){
        Consulta consulta= consultaRepository.findById(idCita).orElse(null);
        if (consulta.getEstado()== EstadoDeConsulta.PROGRAMADA){
            consulta.setEstado(EstadoDeConsulta.CANCELADA);
            consultaRepository.save(consulta);
        }
    }

    public void completarCita(Long idCita){
        Consulta consulta= consultaRepository.findById(idCita).orElse(null);
        if (consulta.getEstado()== EstadoDeConsulta.PROGRAMADA){
            consulta.setEstado(EstadoDeConsulta.COMPLETADA);
            consultaRepository.save(consulta);
        }
    }
}
