package com.manuelberganza.todosapp.repository;

import com.manuelberganza.todosapp.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
