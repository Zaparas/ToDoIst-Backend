package com.todo.challengev2.dto;

import com.todo.challengev2.config.util.PriorityType;
import com.todo.challengev2.domain.ToDoTask;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class ToDoTaskDTO {


    // Variables:
    final private UUID id;
    final private String name;
    final private LocalDate dueDate;

    final private PriorityType priority;

    public ToDoTaskDTO(ToDoTask task) {
        this.id = task.getId();
        this.name = task.getName();
        this.dueDate = task.getDueDate();
        this.priority = task.getPriority();
    }
}
