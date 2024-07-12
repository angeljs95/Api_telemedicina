package com.NoCountry.telemedicinaBack.Services;

import com.NoCountry.telemedicinaBack.Entity.User;
import com.NoCountry.telemedicinaBack.Enum.Role;
import com.NoCountry.telemedicinaBack.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User registrar(User usuario) {
        User user= User.builder()
                .nombre(usuario.getNombre())
                .email(usuario.getEmail())
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

    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(new ArrayList<>())
                .build();
    }
    }

