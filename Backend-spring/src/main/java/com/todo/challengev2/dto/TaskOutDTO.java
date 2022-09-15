package com.todo.challengev2.dto;

import com.todo.challengev2.config.util.PriorityType;
import com.todo.challengev2.domain.Task;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@ToString
public class TaskOutDTO {

    private UUID id;
    private String name;
    private LocalDate dueDate;
    private PriorityType priority;

    public TaskOutDTO(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.dueDate = task.getDueDate();
        this.priority = task.getPriority();
    }
}
