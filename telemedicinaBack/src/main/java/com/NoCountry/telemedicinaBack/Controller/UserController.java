package com.NoCountry.telemedicinaBack.Controller;

import com.NoCountry.telemedicinaBack.Dtos.ActualizarDto;
import com.NoCountry.telemedicinaBack.Dtos.HorariosDto;
import com.NoCountry.telemedicinaBack.Dtos.MedicoDto;
import com.NoCountry.telemedicinaBack.Dtos.PacienteDto;
import com.NoCountry.telemedicinaBack.Entity.Medico;
import com.NoCountry.telemedicinaBack.Entity.Paciente;
import com.NoCountry.telemedicinaBack.Entity.User;
import com.NoCountry.telemedicinaBack.Enum.Role;
import com.NoCountry.telemedicinaBack.Services.MedicoServiceImp;
import com.NoCountry.telemedicinaBack.Services.PacienteServiceImp;
import com.NoCountry.telemedicinaBack.Services.UserService;
//import com.google.api.Http;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MedicoServiceImp medicoService;


    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizar(@RequestBody ActualizarDto actualizarDto) {

    User usuarioActualizado;
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
     //------------Verificamos que tipo de usuario es----------
        if ("paciente".equalsIgnoreCase(user.getRole().toString())) {
            Paciente paciente= userService.buscarPacienteXId(user.getId());
            paciente.setUsername(user.getUsername());
            paciente.setAlergias(actualizarDto.getAlergias());
            paciente.setGenero(actualizarDto.getGenero());
            paciente.setFechaDeNacimiento(actualizarDto.getFechaDeNacimiento());
            paciente.setContacto_emergencia(actualizarDto.getN_contacto());
            usuarioActualizado =userService.actualizarPaciente(paciente);
        } else if("medico".equalsIgnoreCase(user.getRole().toString())){

            Medico medico= userService.buscarMedicoXID(user.getId());
            medico.setId(user.getId());
            medico.setUsername(user.getUsername());
            medico.setConsultorio(actualizarDto.getConsultorio());
            medico.setNum_contacto(actualizarDto.getNum_contacto());
            medico.setEspecialidad(actualizarDto.getEspecialidad());
            medico.setAnios_experiencia(actualizarDto.getAnios_experiencia());
            medico.setN_licencia(actualizarDto.getN_licencia());
            usuarioActualizado= userService.actualizarMedico(medico);
        } else {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarioActualizado);
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<User>> listarUsuarios() {
        List<User> listaDeUsuarios = userService.listarUsuarios();
        return ResponseEntity.ok(listaDeUsuarios);
    }

    @GetMapping("/listarMedicos")
    public ResponseEntity<List<MedicoDto>> listarLosMedicos(){
        List<Medico> listar= medicoService.listarTodosLosMedicos();
        List<MedicoDto> medicoDtos= listar.stream().map(medico -> modelMapper.map(medico, MedicoDto.class))
                .collect(java.util.stream.Collectors.toList());
        return ResponseEntity.ok(medicoDtos);


    }

    @GetMapping("/ver")
    public ResponseEntity<?> mostrarUsuario() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        if (user.getRole()==Role.PACIENTE){
            Paciente paciente= userService.buscarPacienteXId(user.getId());
            return new ResponseEntity<>(modelMapper.map(paciente, PacienteDto.class), HttpStatus.CREATED);
        }
        if (user.getRole()==Role.MEDICO){
            Medico medico = userService.buscarMedicoXID(user.getId());
            return new ResponseEntity<>(modelMapper.map(medico, MedicoDto.class), HttpStatus.CREATED);
        }
            return ResponseEntity.notFound().build();
        }

    @PostMapping("/eliminar")
    public ResponseEntity<Boolean> eliminar(Long id) {
        User usuario = userService.buscarUsuarioPorId(id);
        if (usuario != null) {
            userService.eliminarUsuario(id);
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }

}
