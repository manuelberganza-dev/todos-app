package com.manuelberganza.todosapp.repository;

import com.manuelberganza.todosapp.model.Tarea;
import com.manuelberganza.todosapp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TareaRepository extends JpaRepository<Tarea, Integer> {
    List<Tarea> findByUsuario(Usuario usuario);
}
