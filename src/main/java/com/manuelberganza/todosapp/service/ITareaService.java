package com.manuelberganza.todosapp.service;

import com.manuelberganza.todosapp.model.Tarea;
import com.manuelberganza.todosapp.model.Usuario;

import java.util.List;

public interface ITareaService {
    public void guardarTarea(Tarea tarea);

    public List<Tarea> buscarTareas(Usuario usuario);
    public Tarea buscarTarea(Integer id);
    public void eliminarTarea(Integer id);
}
