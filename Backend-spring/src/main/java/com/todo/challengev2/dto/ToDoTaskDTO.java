package com.todo.challengev2.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.todo.challengev2.config.util.PriorityType;
import com.todo.challengev2.domain.ToDoTask;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class ToDoTaskDTO {
    private UUID id;
    private String name;
    private LocalDate dueDate;
    private PriorityType priority;

    public ToDoTaskDTO(ToDoTask task) {
        this.id = task.getId();
        this.name = task.getName();
        this.dueDate = task.getDueDate();
        this.priority = task.getPriority();
    }
}
