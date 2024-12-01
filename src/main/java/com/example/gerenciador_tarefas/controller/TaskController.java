package com.example.gerenciador_tarefas.controller;

import com.example.gerenciador_tarefas.dto.TaskRequestDTO;
import com.example.gerenciador_tarefas.dto.TaskResponseDTO;
import com.example.gerenciador_tarefas.exception.ResourceNotFoundException;
import com.example.gerenciador_tarefas.model.Task;
import com.example.gerenciador_tarefas.model.User;
import com.example.gerenciador_tarefas.service.TaskService;
import com.example.gerenciador_tarefas.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody @Valid TaskRequestDTO taskRequestDTO,
                                                      @RequestParam String username) {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Task task = taskRequestDTO.toEntity(user);
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.ok(TaskResponseDTO.fromEntity(createdTask));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> listTasks(@RequestParam String username) {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        List<Task> tasks = taskService.findTasksByUser(user);
        List<TaskResponseDTO> taskDTOs = tasks.stream()
                .map(TaskResponseDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(taskDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id,
                                                      @RequestBody @Valid TaskRequestDTO taskRequestDTO) {
        Task existingTask = taskService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        Task updatedTask = taskService.updateTask(existingTask, taskRequestDTO.toEntity(existingTask.getUser()));
        return ResponseEntity.ok(TaskResponseDTO.fromEntity(updatedTask));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        Task task = taskService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        taskService.deleteTask(task);
        return ResponseEntity.noContent().build();
    }
}