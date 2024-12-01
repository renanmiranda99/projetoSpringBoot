package com.example.gerenciador_tarefas.repository;

import com.example.gerenciador_tarefas.model.Task;
import com.example.gerenciador_tarefas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
}