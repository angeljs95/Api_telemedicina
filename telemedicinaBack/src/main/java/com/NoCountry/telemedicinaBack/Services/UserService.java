package com.NoCountry.telemedicinaBack.Services;

import com.NoCountry.telemedicinaBack.Entity.User;
import com.NoCountry.telemedicinaBack.Enum.Role;
import com.NoCountry.telemedicinaBack.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User registrar(User usuario) {
        User user= User.builder()
                .nombre(usuario.getNombre())
                .username(usuario.getUsername())
                .password(passwordEncoder.encode(usuario.getPassword()))
                .role(Role.USUARIO)
                .build();
        return userRepository.save(user);
    }

    @Override
    public void eliminarUsuario(String id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<User> listarUsuarios() {
        return userRepository.findAll();
    }

    @Override
    public User buscarUsuarioPorId(String id) {
        return userRepository.findById(id).orElse(null);
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

