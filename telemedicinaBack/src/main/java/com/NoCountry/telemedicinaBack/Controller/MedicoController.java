package com.NoCountry.telemedicinaBack.Controller;

import com.NoCountry.telemedicinaBack.Dtos.HorariosDto;
import com.NoCountry.telemedicinaBack.Entity.*;
import com.NoCountry.telemedicinaBack.Services.HorarioDeAtencionService;
import com.NoCountry.telemedicinaBack.Services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/medico")
public class MedicoController {
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HorarioDeAtencionService horarioService;

    @GetMapping("/{medicoId}")
    public ResponseEntity<List<HorariosDto>> obtenerHorariosPorDoctor(@PathVariable Long medicoId) {
        List<HorarioDeAtencion> horarios = horarioService.obtenerTodosLosHorariosPorMedico(medicoId);
        List<HorariosDto> horariosDto=  horarios.stream().map(horarioDeAtencion -> modelMapper.map(horarioDeAtencion, HorariosDto.class))
                .collect(java.util.stream.Collectors.toList());

        return new ResponseEntity<>(horariosDto, HttpStatus.OK);
    }
    @PostMapping("/horarios")
    public ResponseEntity<Boolean> guardarHorario(@RequestBody HorarioDeAtencion horarioAtencion) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        Medico medico= userService.buscarMedicoXID(user.getId());
        horarioAtencion.setMedico(medico);
        HorarioDeAtencion nuevoHorario = horarioService.guardarHorarioAtencion(horarioAtencion);
        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }
    @PostMapping("/horarios/{idHorario}")
    public ResponseEntity<String> habilitarHorario(@PathVariable Long idHorario){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        horarioService.habilitarHorarios(idHorario);
        return new ResponseEntity<>("Se ha habilitado la fecha", HttpStatus.CREATED);
    }
    @DeleteMapping("/horarios/{idHorario}")
    public ResponseEntity<Boolean> eliminarHorario(@PathVariable Long idHorario){
        horarioService.eliminarHorario(idHorario);
        return new ResponseEntity<>(true, HttpStatus.CREATED);

    }





}
