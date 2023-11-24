package com.manuelberganza.todosapp.service;

import com.manuelberganza.todosapp.model.Categoria;
import com.manuelberganza.todosapp.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService implements ICategoriaService {

    @Autowired
    CategoriaRepository categoriaRepo;

    @Override
    public List<Categoria> obtenerTodasCategorias() {
        return categoriaRepo.findAll();
    }

}
