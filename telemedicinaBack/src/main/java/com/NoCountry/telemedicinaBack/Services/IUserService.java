package com.NoCountry.telemedicinaBack.Services;

import com.NoCountry.telemedicinaBack.Entity.User;

import java.util.List;

public interface IUserService {

    public User registrar(User user);

    public void eliminarUsuario(Long id);

    public List<User> listarUsuarios();

    public User buscarUsuarioPorId(Long id);
}
