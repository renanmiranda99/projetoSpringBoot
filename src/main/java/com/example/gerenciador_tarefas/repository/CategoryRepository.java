package com.example.gerenciador_tarefas.repository;

import com.example.gerenciador_tarefas.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}