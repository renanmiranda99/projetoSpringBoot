package com.example.gerenciador_tarefas.dto;

import com.example.gerenciador_tarefas.model.Task;
import lombok.Data;

@Data
public class TaskResponseDTO {

    private Long id;
    private String title;
    private String description;
    private String status;
    private String username;

    public static TaskResponseDTO fromEntity(Task task) {
        TaskResponseDTO dto = new TaskResponseDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus().name());
        dto.setUsername(task.getUser().getUsername());
        return dto;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}