package com.todo.challengev2.dto;

import com.todo.challengev2.config.util.PriorityType;
import com.todo.challengev2.domain.ToDoTask;
import lombok.Getter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class ToDoTaskOutputDTO {

    private UUID id;
    private String name;
    private LocalDate dueDate;
    private PriorityType priority;

    public ToDoTaskOutputDTO(ToDoTask task) {
        this.id = task.getId();
        this.name = task.getName();
        this.dueDate = task.getDueDate();
        this.priority = task.getPriority();
    }
}
