package com.NoCountry.telemedicinaBack.Services;

import com.NoCountry.telemedicinaBack.Entity.Medico;
import com.NoCountry.telemedicinaBack.Entity.Paciente;
import com.NoCountry.telemedicinaBack.Entity.User;
import com.NoCountry.telemedicinaBack.Enum.Role;
import com.NoCountry.telemedicinaBack.Repository.PacienteRepository;
import com.NoCountry.telemedicinaBack.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private MedicoServiceImp medicoServiceImp;
    @Autowired
    private PacienteRepository pacienteRepository;


    @Override
    public User registrar(User usuario){

        User user= User.builder()
                .username(usuario.getUsername())
                .password(passwordEncoder.encode(usuario.getPassword()))
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .documento(usuario.getDocumento())
                .pais(usuario.getPais())
                .localidad(usuario.getLocalidad())
                .provincia(usuario.getProvincia())
                .telefono(usuario.getTelefono())
                .estado(true)
                .role(usuario.getRole())
                .fecha_Registro(LocalDate.now())
                .build();

        if(user.getRole()== Role.MEDICO){
            Medico medico= new Medico(user);
        userRepository.save(medico);
        } else if( user.getRole()== Role.PACIENTE){
            Paciente paciente= new Paciente(user);
            userRepository.save(paciente);
        }
        return user;
    }

    public Medico actualizarMedico(Medico medico){
        return userRepository.save(medico);}
    public Paciente actualizarPaciente(Paciente paciente){
        return userRepository.save(paciente);
    }


    @Override
    public void eliminarUsuario(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> listarUsuarios() {
        return userRepository.findAll();
    }

    @Override
    public User buscarUsuarioPorId(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    public Medico buscarMedicoXID(Long id){
        return medicoServiceImp.findByIdMedico(id);
    }

    public Paciente buscarPacienteXId(Long id){
        return pacienteRepository.findById(id).orElse(null);
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username).orElse(null);
    }




   /* @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
       return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(new ArrayList<>())
                .build();
        //return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }*/
    }

