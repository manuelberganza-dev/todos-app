package com.manuelberganza.todosapp.service;

import com.manuelberganza.todosapp.model.Usuario;

public interface IUsuarioService {
    public void guardarUsuario(Usuario usuario);
    public Usuario buscarUsuario(String username);
}
