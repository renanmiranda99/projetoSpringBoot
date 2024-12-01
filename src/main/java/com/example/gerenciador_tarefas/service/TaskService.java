package com.example.gerenciador_tarefas.service;

import com.example.gerenciador_tarefas.model.Status;
import com.example.gerenciador_tarefas.model.Task;
import com.example.gerenciador_tarefas.model.User;
import com.example.gerenciador_tarefas.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findTasksByUser(User user) {
        return taskRepository.findByUser(user);
    }

    public Task createTask(Task task) {
        task.setStatus(Status.PENDING);
        return taskRepository.save(task);
    }

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    public Task updateTask(Task task, Task updatedData) {
        task.setTitle(updatedData.getTitle());
        task.setDescription(updatedData.getDescription());
        task.setStatus(updatedData.getStatus());
        return taskRepository.save(task);
    }

    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }
}