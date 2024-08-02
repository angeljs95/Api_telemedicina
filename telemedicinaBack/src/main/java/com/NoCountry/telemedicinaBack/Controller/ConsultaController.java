package com.NoCountry.telemedicinaBack.Controller;

import com.NoCountry.telemedicinaBack.Dtos.ConsultaDto;
import com.NoCountry.telemedicinaBack.Dtos.HorariosDto;
import com.NoCountry.telemedicinaBack.Dtos.MedicoDto;
import com.NoCountry.telemedicinaBack.Entity.*;
import com.NoCountry.telemedicinaBack.Enum.Role;
import com.NoCountry.telemedicinaBack.Services.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchTransactionManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;
    @Autowired
    private HojaClinicaService hojaClinicaService;
    @Autowired
    private UserService userService;
    @Autowired
    private HorarioDeAtencionService horarioService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/ver/{consultaId}")
    public ResponseEntity<ConsultaDto> verConsulta(@PathVariable Long consultaId){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        ConsultaDto consulta= consultaService.verConsulta(consultaId);
        return new ResponseEntity<>(consulta, HttpStatus.CREATED);
    }

    @GetMapping(" ")
    public ResponseEntity<List<ConsultaDto>> consultas(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        if(user.getRole()== Role.PACIENTE){
            return new ResponseEntity<>(consultaService.listarConsultasPaciente(user.getId()), HttpStatus.CREATED);
        }
        if (user.getRole()== Role.MEDICO){
            return new ResponseEntity<>(consultaService.listarConsultasMedico(user.getId()), HttpStatus.CREATED);
        }
    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/{medicoId}")
    public ResponseEntity<List<HorariosDto>> obtenerHorariosPorDoctor(@PathVariable Long medicoId) {
        List<HorarioDeAtencion> horarios = horarioService.obtenerTodosLosHorariosPorMedico(medicoId);
        List <HorariosDto> horariosDtos= horarios.stream().map(horario ->modelMapper.map(horario, HorariosDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(horariosDtos, HttpStatus.OK);
    }

    @GetMapping("/horariosDisponible/{medicoId}")
    public ResponseEntity<List<HorariosDto>> obtenerHorariosDisponiblesPorMedico(@PathVariable Long medicoId) {
        List<HorarioDeAtencion> horarios = horarioService.obtenerHorariosDisponiblesPorMedico(medicoId);
        List <HorariosDto> horariosDtos= horarios.stream().map(horario ->modelMapper.map(horario, HorariosDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(horariosDtos, HttpStatus.OK);
    }

    @PostMapping("/agendar/{horarioId}")
    public ResponseEntity<String> agendarCita(@PathVariable Long horarioId,
                                                @RequestBody ConsultaDto consultaDto) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        Paciente paciente= userService.buscarPacienteXId(user.getId());
        Medico medico = userService.buscarMedicoXID(consultaDto.getIdMedico());
        consultaDto.setHorarioId(horarioId);
        Consulta cita = consultaService.agendarCita(consultaDto, paciente, medico);
        hojaClinicaService.guardarHoja(cita.getHojaClinica());
        return new ResponseEntity<>("Consulta agendada Exitosamente", HttpStatus.CREATED);
    }

    @PutMapping("cancelar/{idCita}")
    public ResponseEntity<String> cancelarCita(@PathVariable Long idCita){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        consultaService.cancelarCita(idCita);
        return new ResponseEntity<>("Consulta cancelada Exitosamente", HttpStatus.CREATED);
    }
    @PutMapping("/{idCita}")
    public ResponseEntity<String> completarCita(@PathVariable Long idCita){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        if(user.getRole()== Role.MEDICO){
        consultaService.completarCita(idCita);
            return new ResponseEntity<>("Consulta finalizada", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Ha ocurrido un error", HttpStatus.BAD_REQUEST);
    }

}
