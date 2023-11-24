package com.manuelberganza.todosapp.service;

import com.manuelberganza.todosapp.model.Usuario;
import com.manuelberganza.todosapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService{

    @Autowired
    UsuarioRepository usuarioRepo;

    @Override
    public void guardarUsuario(Usuario usuario) {
        usuarioRepo.save(usuario);
    }

    @Override
    public Usuario buscarUsuario(String username) {
        return usuarioRepo.findByUsername(username);
    }
}
