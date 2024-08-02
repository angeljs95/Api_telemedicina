package com.NoCountry.telemedicinaBack.Controller;

import com.NoCountry.telemedicinaBack.Dtos.*;
import com.NoCountry.telemedicinaBack.Entity.HojaClinica;
import com.NoCountry.telemedicinaBack.Entity.Medico;
import com.NoCountry.telemedicinaBack.Entity.Paciente;
import com.NoCountry.telemedicinaBack.Entity.User;
import com.NoCountry.telemedicinaBack.Enum.EstadoDeConsulta;
import com.NoCountry.telemedicinaBack.Enum.Role;
import com.NoCountry.telemedicinaBack.Exceptions.MiException;
import com.NoCountry.telemedicinaBack.Services.ConsultaService;
import com.NoCountry.telemedicinaBack.Services.HojaClinicaService;
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
@RequestMapping("api/hojasClinicas")
public class HojaClinicaController {

    @Autowired
    private HojaClinicaService hojaClinicaService;
    @Autowired
    private UserService userService;
    @Autowired
    private ConsultaService consultaService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{consultaId}")
    public ResponseEntity<HojaClinicaDto> verHojaClinica(@PathVariable Long consultaId){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        if(user.getRole()== Role.MEDICO){
            HojaClinicaDto dto= hojaClinicaService.traerHojaClinica(consultaId);
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/{consultaId}/verHistorial/{pacienteId}")
    public ResponseEntity<List<HojaClinicaDto>> verHistoriasDelPaciente(@PathVariable Long pacienteId, @PathVariable Long consultaId){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        if (user.getRole() == Role.MEDICO) {
           if(consultaService.verConsulta(consultaId).getEstado()== EstadoDeConsulta.PROGRAMADA){
               List<HojaClinicaDto> listas= hojaClinicaService.verHistorialMedico(pacienteId);
               return new ResponseEntity<>(listas, HttpStatus.CREATED);
           }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping ("/{consultaId}")
    public ResponseEntity<?> agregarHojaClinicaPostConsulta(@PathVariable Long consultaId ,@RequestBody HojaClinicaDto hojaClinicaDto){

        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userService.findByUsername(userDetails.getUsername());
            if(user.getRole()== Role.MEDICO) {
            hojaClinicaDto.setConsultaId(consultaId);
                hojaClinicaService.actualizarHojaClinica(hojaClinicaDto);
                return new ResponseEntity<>("Se ha guardado exitosamente la ficha Clinica", HttpStatus.CREATED);
            }
            if(user.getRole()== Role.PACIENTE){
                return new ResponseEntity<>("no tiene autoridad para modificar la ficha clinica", HttpStatus.BAD_REQUEST);
            }

        } catch (MiException e) {
            throw new RuntimeException(e);
        }
return null;
}



}
