package com.example.gerenciador_tarefas.dto;

import com.example.gerenciador_tarefas.model.Status;
import com.example.gerenciador_tarefas.model.Task;
import com.example.gerenciador_tarefas.model.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TaskRequestDTO {

    @NotBlank(message = "Title cannot be empty")
    private String title;

    @NotBlank(message = "Description cannot be empty")
    private String description;

    private String status;

    public Task toEntity(User user) {
        Task task = new Task();
        task.setTitle(this.title);
        task.setDescription(this.description);
        task.setStatus(this.status != null ? Status.valueOf(this.status.toUpperCase()) : Status.PENDING);
        task.setUser(user);
        return task;
    }
}