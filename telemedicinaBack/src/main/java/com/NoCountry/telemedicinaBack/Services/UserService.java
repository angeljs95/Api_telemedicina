package com.NoCountry.telemedicinaBack.Services;

import com.NoCountry.telemedicinaBack.Entity.User;
import com.NoCountry.telemedicinaBack.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public User registrar(User usuario) {
        User user= User.builder()
                .nombre(usuario.getNombre())
                .correo(usuario.getCorreo())
                .contrasenia(usuario.getContrasenia())
                .build();
        return user;
    }

    @Override
    public void eliminarUsuario(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> listarUsuarios() {
        return userRepository.findAll();
    }

    @Override
    public User buscarUsuarioPorId(String id) {
        return userRepository.findById(id).orElse(null);
    }
}
