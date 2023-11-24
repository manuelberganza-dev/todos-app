package com.manuelberganza.todosapp.service;

import com.manuelberganza.todosapp.model.Tarea;
import com.manuelberganza.todosapp.model.Usuario;
import com.manuelberganza.todosapp.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaService implements ITareaService {

    @Autowired
    TareaRepository tareaRepo;
    @Override
    public void guardarTarea(Tarea tarea) {
        tareaRepo.save(tarea);
    }

    @Override
    public List<Tarea> buscarTareas(Usuario usuario) {
        return tareaRepo.findByUsuario(usuario);
    }

    @Override
    public Tarea buscarTarea(Integer id) {
        Optional<Tarea> optional = tareaRepo.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }

        return null;
    }

    @Override
    public void eliminarTarea(Integer id) {
        tareaRepo.deleteById(id);
    }
}
