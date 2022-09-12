package com.todo.challengev2.dto;

import com.todo.challengev2.config.util.PriorityType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ToDoTaskInputDTO {

    private String name;
    private LocalDate dueDate;
    private PriorityType priority;

    public ToDoTaskInputDTO(ToDoTaskOutputDTO task) {
        this.name = task.getName();
        this.dueDate = task.getDueDate();
        this.priority = task.getPriority();
    }
}
